package com.study.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Word 转 PDF 工具类  带缓存功能
 * Windows 优先使用 WPS/Word COM 自动化（等于"另存为PDF"），Linux 使用 LibreOffice headless
 */
@Slf4j
public class WordToPdfUtilCache {

    /** LibreOffice 可执行文件路径，自动探测 */
    private static volatile String sofficeCmd;

    /** 转换超时时间（秒） */
    private static final int TIMEOUT_SECONDS = 120;

    /** 缓存目录 */
    private static final String CACHE_DIR_NAME = "word2pdf_cache";

    /** 内存缓存：文件MD5 -> PDF缓存文件路径 */
    private static final Map<String, File> cacheMap = new ConcurrentHashMap<>();

    /** 是否启用缓存 */
    private static volatile boolean cacheEnabled = true;

    /** 缓存目录路径 */
    private static volatile File cacheDir;

    static {
        initCacheDir();
    }

    private WordToPdfUtilCache() {
    }

    /**
     * 初始化缓存目录
     */
    private static void initCacheDir() {
        try {
            String tempDirPath = System.getProperty("java.io.tmpdir");
            cacheDir = new File(tempDirPath, CACHE_DIR_NAME);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            log.info("Word转PDF缓存目录: {}", cacheDir.getAbsolutePath());

            // 加载已有缓存到内存
            loadExistingCache();
        } catch (Exception e) {
            log.warn("初始化缓存目录失败，将禁用缓存功能", e);
            cacheEnabled = false;
        }
    }

    /**
     * 加载已存在的缓存文件到内存
     */
    private static void loadExistingCache() {
        if (cacheDir == null || !cacheDir.exists()) {
            return;
        }

        File[] cacheFiles = cacheDir.listFiles((dir, name) -> name.endsWith(".pdf"));
        if (cacheFiles == null) {
            return;
        }

        for (File pdfFile : cacheFiles) {
            String fileName = pdfFile.getName();
            // 缓存文件名格式: {MD5}.pdf
            String md5 = fileName.substring(0, fileName.length() - 4);
            if (md5.matches("^[a-f0-9]{32}$")) {
                cacheMap.put(md5, pdfFile);
            }
        }
        log.info("加载了 {} 个PDF缓存文件", cacheMap.size());
    }

    /**
     * 计算文件的MD5值
     */
    private static String getFileMD5(File file) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    md.update(buffer, 0, bytesRead);
                }
            }

            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("MD5算法不可用", e);
        }
    }

    /**
     * 从缓存获取PDF文件
     */
    private static File getCachedPdf(String fileMd5) {
        if (!cacheEnabled) {
            return null;
        }

        File cachedFile = cacheMap.get(fileMd5);
        if (cachedFile != null && cachedFile.exists() && cachedFile.length() > 0) {
            log.info("命中缓存，使用已转换的PDF: {}", cachedFile.getAbsolutePath());
            return cachedFile;
        }

        // 缓存文件无效，从内存中移除
        if (cachedFile != null) {
            cacheMap.remove(fileMd5);
        }
        return null;
    }

    /**
     * 保存PDF到缓存
     */
    private static void saveToCache(String fileMd5, File pdfFile) throws IOException {
        if (!cacheEnabled || cacheDir == null) {
            return;
        }

        File cacheFile = new File(cacheDir, fileMd5 + ".pdf");
        Files.copy(pdfFile.toPath(), cacheFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        cacheMap.put(fileMd5, cacheFile);
        log.info("PDF已保存到缓存: {}", cacheFile.getAbsolutePath());
    }

    /**
     * 将 Word 文件转为 PDF 临时文件（带缓存）
     *
     * @param wordFile Word 文件（.doc 或 .docx）
     * @return 生成的 PDF 临时文件
     */
    public static File convert(File wordFile) throws IOException {
        return convert(wordFile, true);
    }

    /**
     * 将 Word 文件转为 PDF 临时文件
     *
     * @param wordFile Word 文件（.doc 或 .docx）
     * @param useCache 是否使用缓存
     * @return 生成的 PDF 临时文件
     */
    public static File convert(File wordFile, boolean useCache) throws IOException {
        if (wordFile == null || !wordFile.exists()) {
            throw new IOException("Word文件不存在: " + wordFile);
        }

        // 计算文件MD5用于缓存
        String fileMd5 = getFileMD5(wordFile);

        // 检查缓存
        if (useCache) {
            File cachedPdf = getCachedPdf(fileMd5);
            if (cachedPdf != null) {
                // 创建临时文件副本返回（避免直接修改缓存文件）
                File tempDir = Files.createTempDirectory("word2pdf_").toFile();
                tempDir.deleteOnExit();
                File resultPdf = new File(tempDir, wordFile.getName().replaceAll("\\.[^.]+$", ".pdf"));
                Files.copy(cachedPdf.toPath(), resultPdf.toPath());
                return resultPdf;
            }
        }

        // 执行转换
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

        // 保存到缓存
        if (useCache) {
            try {
                saveToCache(fileMd5, pdfFile);
            } catch (Exception e) {
                log.warn("保存缓存失败", e);
            }
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
        return convert(wordFile, outputPath, true);
    }

    /**
     * 将 Word 文件转为指定路径的 PDF 文件
     *
     * @param wordFile   Word 文件（.doc 或 .docx）
     * @param outputPath PDF 输出路径
     * @param useCache   是否使用缓存
     * @return 生成的 PDF 文件
     */
    public static File convert(File wordFile, String outputPath, boolean useCache) throws IOException {
        File tempPdf = convert(wordFile, useCache);
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
     * 清除所有缓存
     */
    public static void clearCache() {
        if (cacheDir != null && cacheDir.exists()) {
            File[] files = cacheDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
        cacheMap.clear();
        log.info("缓存已清除");
    }

    /**
     * 清除指定文件的缓存
     */
    public static void clearCache(File wordFile) throws IOException {
        if (!wordFile.exists()) {
            return;
        }
        String md5 = getFileMD5(wordFile);
        File cacheFile = cacheMap.remove(md5);
        if (cacheFile != null && cacheFile.exists()) {
            cacheFile.delete();
        }
        log.info("已清除文件缓存: {}", wordFile.getName());
    }

    /**
     * 获取缓存统计信息
     */
    public static String getCacheStats() {
        long totalSize = 0;
        for (File file : cacheMap.values()) {
            if (file.exists()) {
                totalSize += file.length();
            }
        }
        return String.format("缓存文件数: %d, 总大小: %.2f MB",
                cacheMap.size(), totalSize / (1024.0 * 1024.0));
    }

    /**
     * 设置是否启用缓存
     */
    public static void setCacheEnabled(boolean enabled) {
        cacheEnabled = enabled;
        log.info("缓存功能已{}", enabled ? "启用" : "禁用");
    }

    // 以下是原有的转换方法，保持不变...
    private static void convertByWpsOrWord(File wordFile, File pdfFile) throws IOException {
        // ... 保持原有代码不变
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

        ps1File.delete();
    }

    private static void convertByLibreOffice(File wordFile, File outDir) throws IOException {
        // ... 保持原有代码不变
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

    private static String getSofficeCmd() throws IOException {
        // ... 保持原有代码不变
        if (sofficeCmd != null) {
            return sofficeCmd;
        }

        synchronized (WordToPdfUtil.class) {
            if (sofficeCmd != null) {
                return sofficeCmd;
            }

            String[] candidates = {
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
            }

            throw new IOException("未找到LibreOffice，请安装LibreOffice并确保 soffice 可执行。"
                    + "Linux: yum install libreoffice / apt install libreoffice");
        }
    }
}
