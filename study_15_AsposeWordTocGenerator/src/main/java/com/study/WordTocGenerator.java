package com.study;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/19/10:36
 * @Description:
 */
public class WordTocGenerator { // 标题实体：文本 + 层级


    public static void main(String[] args) {
        String inputPath = "D:\\study-notes\\study_15_AsposeWordTocGenerator\\src\\main\\resources\\XX公司信息科技风险评估报告模板.docx";          // 原始文档
        String outputPath = "D:\\study-notes\\study_15_AsposeWordTocGenerator\\src\\main\\resources\\XX公司信息科技风险评估报告模板.docx_toc.docx";  // 最终输出
        try {
            // 步骤1: 标准化标题样式（关键！）
            File normalizedFile = normalizeDocument(inputPath);

            // 步骤2: 插入 TOC 域替换 {{目录}}
            File tocFile = insertTOC(normalizedFile);

            // 步骤3: 调用 LibreOffice 刷新字段（更新页码）
            refreshWithLibreOffice(tocFile, outputPath);

            // 清理临时文件
            normalizedFile.delete();
            tocFile.delete();

            System.out.println("✅ 目录生成并刷新完成: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 步骤1: 尝试将标题段落标准化为 Heading 1/2/3
    private static File normalizeDocument(String inputPath) throws IOException {
        File tempFile = new File("temp_normalized.docx");
        try (FileInputStream fis = new FileInputStream(inputPath);
             FileOutputStream fos = new FileOutputStream(tempFile)) {

            XWPFDocument doc = new XWPFDocument(fis);

            for (XWPFParagraph para : doc.getParagraphs()) {
                String text = para.getText();
                if (text == null || text.trim().isEmpty()) continue;

                // 启发式规则：根据文本特征判断标题级别（您可根据实际调整）
                if (text.matches("第[一二三四五六七八九十]+章\\s+.+")) {
                    para.setStyle("Heading 1");
                } else if (text.matches("\\d+\\.\\s+.+") || text.matches("\\d+\\.\\d+\\s+.+")) {
                    // 1. 引言 → Heading 1；1.1 背景 → Heading 2
                    long dotCount = text.chars().filter(ch -> ch == '.').count();
                    if (dotCount == 1 && text.matches("\\d+\\.\\s+.+")) {
                        para.setStyle("Heading 1");
                    } else if (dotCount >= 1) {
                        para.setStyle("Heading 2");
                    }
                }
                // TODO: 可继续添加更多规则（如字体大小、加粗等）
            }

            doc.write(fos);
            return tempFile;
        }
    }

    // 步骤2: 替换 {{目录}} 为 TOC 域
    private static File insertTOC(File inputFile) throws IOException {
        File outputFile = new File("temp_with_toc.docx");
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            XWPFDocument doc = new XWPFDocument(fis);
            boolean found = false;

            for (XWPFParagraph para : doc.getParagraphs()) {
                String paraText = para.getText() == null ? "" : para.getText().trim();
                if (paraText.contains("{{目录}}")) {
                    // ========== 关键修正：安全清空段落中的所有Run ==========
                    // 替换 para.getRuns().clear() 为逐个删除Run
                    List<XWPFRun> runs = para.getRuns();
                    while (runs.size() > 0) {
                        para.removeRun(0); // 逐个删除，避免不可修改集合报错
                    }

                    // 插入目录域（保留你的原有逻辑）
                    insertTOCField(para);
                    found = true;
                    break;
                }
            }

            /*if (!found) {
                System.out.println("⚠️ 未找到 {{目录}}，将在开头插入目录");
                XWPFParagraph firstPara = doc.insertNewParagraph(
                        doc.getDocument().getBody().addNewP()
                );
                insertTOCField(firstPara);
            }*/

            doc.write(fos);
            return outputFile;
        }
    }

    // 插入标准 TOC 域
    private static void insertTOCField(XWPFParagraph paragraph) {
        XWPFRun begin = paragraph.createRun();
        begin.getCTR().addNewFldChar().setFldCharType(STFldCharType.BEGIN);

        XWPFRun code = paragraph.createRun();
        CTText instr = code.getCTR().addNewInstrText();
        instr.setStringValue("TOC \\o \"1-3\" \\h \\z \\u");

        XWPFRun sep = paragraph.createRun();
        sep.getCTR().addNewFldChar().setFldCharType(STFldCharType.SEPARATE);

        XWPFRun end = paragraph.createRun();
        end.getCTR().addNewFldChar().setFldCharType(STFldCharType.END);
    }

    // 步骤3: 调用 LibreOffice 刷新字段
    private static void refreshWithLibreOffice(File inputFile, String outputFilePath) throws Exception {
        File outputFile = new File(outputFilePath);
        String outputDir = outputFile.getParentFile().getAbsolutePath();
        String inputAbsPath = inputFile.getAbsolutePath();

        // 构建命令（兼容 Windows/Linux/macOS）
        String sofficeCmd = System.getProperty("os.name").toLowerCase().contains("win")
                ? "soffice.exe"
                : "soffice";

        String command = String.format(
                "%s --headless --invisible --convert-to docx --outdir \"%s\" \"%s\"",
                sofficeCmd, outputDir, inputAbsPath
        );

        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            // 打印错误信息便于调试
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.err.println(line);
                }
            }
            throw new RuntimeException("LibreOffice 执行失败，退出码: " + exitCode);
        }

        // LibreOffice 输出文件名与输入相同，需重命名
        File libreOutput = new File(outputDir, inputFile.getName());
        if (!libreOutput.exists()) {
            throw new FileNotFoundException("LibreOffice 未生成输出文件: " + libreOutput);
        }
        Files.move(libreOutput.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
