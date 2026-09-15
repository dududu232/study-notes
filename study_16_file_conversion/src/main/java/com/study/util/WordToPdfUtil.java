package com.study.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

/**
 * Word 转 PDF 工具类
 * Windows 优先使用 WPS/Word COM 自动化（等于"另存为PDF"），Linux 使用 LibreOffice headless
 */
@Slf4j
public class WordToPdfUtil {

    /** LibreOffice 可执行文件路径，自动探测 */
    private static volatile String sofficeCmd;

    /** 转换超时时间（秒） */
    private static final int TIMEOUT_SECONDS = 120;

    private WordToPdfUtil() {
    }

    /**
     * 将 Word 文件转为 PDF 临时文件
     *
     * @param wordFile Word 文件（.doc 或 .docx）
     * @return 生成的 PDF 临时文件
     */
    public static File convert(File wordFile) throws IOException {
        if (wordFile == null || !wordFile.exists()) {
            throw new IOException("Word文件不存在: " + wordFile);
        }

        File tempDir = Files.createTempDirectory("word2pdf_").toFile();
        tempDir.deleteOnExit();

        String pdfName = wordFile.getName().replaceAll("\\.[^.]+$", ".pdf");
        File pdfFile = new File(tempDir, pdfName);

        boolean isWindows = System.getProperty("os.name", "").toLowerCase().contains("win");

        if (isWindows) {
            // Windows: 优先用 WPS/Word COM 自动化
            convertByWpsOrWord(wordFile, pdfFile);
        } else {
            // Linux: 用 LibreOffice headless
            convertByLibreOffice(wordFile, tempDir);
        }

        if (!pdfFile.exists()) {
            throw new IOException("转换后未找到 PDF 文件: " + pdfFile.getAbsolutePath());
        }
        log.info("Word转PDF完成: {} -> {}, 文件大小: {} bytes",
                wordFile.getName(), pdfFile.getAbsolutePath(), pdfFile.length());
        return pdfFile;
    }

    /**
     * 将 Word 文件转为指定路径的 PDF 文件
     *
     * @param wordFile   Word 文件（.doc 或 .docx）
     * @param outputPath PDF 输出路径
     * @return 生成的 PDF 文件
     */
    public static File convert(File wordFile, String outputPath) throws IOException {
        File tempPdf = convert(wordFile);
        File outputFile = new File(outputPath);
        File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        Files.move(tempPdf.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        log.info("PDF已移动到: {}", outputFile.getAbsolutePath());
        return outputFile;
    }

    /**
     * Windows 下通过 PowerShell 调用 WPS/Word COM 自动化，等于"另存为PDF"
     */
    private static void convertByWpsOrWord(File wordFile, File pdfFile) throws IOException {
        // 使用 UTF-8 BOM 写入 ps1 文件，确保 PowerShell 正确识别编码
        String ps1Content = "$ErrorActionPreference = 'Stop'\n"
                + "$inputPath = '" + wordFile.getAbsolutePath().replace("'", "''") + "'\n"
                + "$outputPath = '" + pdfFile.getAbsolutePath().replace("'", "''") + "'\n"
                + "$app = $null\n"
                + "$doc = $null\n"
                + "try {\n"
                + "    try {\n"
                + "        $app = New-Object -ComObject 'KWPS.Application'\n"
                + "        Write-Host 'Using WPS Office'\n"
                + "    } catch {\n"
                + "        $app = New-Object -ComObject 'Word.Application'\n"
                + "        Write-Host 'Using Microsoft Word'\n"
                + "    }\n"
                + "    $app.Visible = $false\n"
                + "    $app.DisplayAlerts = 0\n"
                + "    $doc = $app.Documents.Open($inputPath)\n"
                + "    $doc.SaveAs2([ref]$outputPath, [ref]17)\n"
                + "    $doc.Close([ref]0)\n"
                + "    $doc = $null\n"
                + "    $app.Quit()\n"
                + "    $app = $null\n"
                + "    Write-Host 'PDF exported successfully'\n"
                + "} catch {\n"
                + "    Write-Host \"ERROR: $($_.Exception.Message)\"\n"
                + "    if ($doc) { try { $doc.Close([ref]0) } catch {} }\n"
                + "    if ($app) { try { $app.Quit() } catch {} }\n"
                + "    exit 1\n"
                + "} finally {\n"
                + "    [System.GC]::Collect()\n"
                + "    [System.GC]::WaitForPendingFinalizers()\n"
                + "}\n";

        File ps1File = Files.createTempFile("word2pdf_", ".ps1").toFile();
        ps1File.deleteOnExit();
        // 写入 UTF-8 BOM + 内容，PowerShell 会自动识别 BOM 编码
        byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        byte[] content = ps1Content.getBytes(StandardCharsets.UTF_8);
        byte[] withBom = new byte[bom.length + content.length];
        System.arraycopy(bom, 0, withBom, 0, bom.length);
        System.arraycopy(content, 0, withBom, bom.length, content.length);
        Files.write(ps1File.toPath(), withBom);

        ProcessBuilder pb = new ProcessBuilder(
                "powershell.exe",
                "-NoProfile",
                "-ExecutionPolicy", "Bypass",
                "-File", ps1File.getAbsolutePath()
        );
        pb.redirectErrorStream(true);

        log.info("执行WPS/Word COM转换: {} -> {}", wordFile.getName(), pdfFile.getAbsolutePath());

        Process process = pb.start();
        StringBuilder output = new StringBuilder();
        // PowerShell 输出使用系统默认编码（GBK on 中文 Windows）
        Charset charset = Charset.forName(System.getProperty("sun.jnu.encoding", "GBK"));
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), charset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        boolean finished;
        try {
            finished = process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            process.destroyForcibly();
            throw new IOException("WPS/Word转换被中断", e);
        }

        if (!finished) {
            process.destroyForcibly();
            throw new IOException("WPS/Word转换超时（" + TIMEOUT_SECONDS + "秒）");
        }

        int exitCode = process.exitValue();
        log.info("WPS/Word转换输出: {}", output.toString().trim());

        if (exitCode != 0) {
            throw new IOException("WPS/Word转换失败，退出码: " + exitCode + "，输出: " + output);
        }

        // 清理临时脚本
        ps1File.delete();
    }

    /**
     * 调用 LibreOffice headless 进行转换（Linux 环境）
     */
    private static void convertByLibreOffice(File wordFile, File outDir) throws IOException {
        String cmd = getSofficeCmd();

        ProcessBuilder pb = new ProcessBuilder(
                cmd,
                "--headless",
                "--convert-to", "pdf",
                "--outdir", outDir.getAbsolutePath(),
                wordFile.getAbsolutePath()
        );
        pb.redirectErrorStream(true);

        log.info("执行LibreOffice转换: {} -> {}", wordFile.getName(), outDir.getAbsolutePath());

        Process process = pb.start();
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        boolean finished;
        try {
            finished = process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            process.destroyForcibly();
            throw new IOException("LibreOffice转换被中断", e);
        }

        if (!finished) {
            process.destroyForcibly();
            throw new IOException("LibreOffice转换超时（" + TIMEOUT_SECONDS + "秒）");
        }

        int exitCode = process.exitValue();
        if (exitCode != 0) {
            throw new IOException("LibreOffice转换失败，退出码: " + exitCode + "，输出: " + output);
        }

        log.debug("LibreOffice输出: {}", output.toString().trim());
    }

    /**
     * 自动探测 LibreOffice soffice 可执行文件路径
     */
    private static String getSofficeCmd() throws IOException {
        if (sofficeCmd != null) {
            return sofficeCmd;
        }

        synchronized (WordToPdfUtil.class) {
            if (sofficeCmd != null) {
                return sofficeCmd;
            }

            // 常见安装路径
            String[] candidates = {
                    // Linux
                    "/usr/bin/soffice",
                    "/usr/bin/libreoffice",
                    "/usr/local/bin/soffice",
                    "/opt/libreoffice/program/soffice",
            };

            for (String path : candidates) {
                if (new File(path).exists()) {
                    sofficeCmd = path;
                    log.info("找到LibreOffice: {}", sofficeCmd);
                    return sofficeCmd;
                }
            }

            // 尝试 PATH 中查找
            try {
                ProcessBuilder pb = new ProcessBuilder("soffice", "--version");
                pb.redirectErrorStream(true);
                Process p = pb.start();
                boolean ok = p.waitFor(5, TimeUnit.SECONDS);
                if (ok && p.exitValue() == 0) {
                    sofficeCmd = "soffice";
                    log.info("在PATH中找到LibreOffice");
                    return sofficeCmd;
                }
            } catch (Exception ignored) {
                // soffice not in PATH
            }

            throw new IOException("未找到LibreOffice，请安装LibreOffice并确保 soffice 可执行。"
                    + "Linux: yum install libreoffice / apt install libreoffice");
        }
    }
}
