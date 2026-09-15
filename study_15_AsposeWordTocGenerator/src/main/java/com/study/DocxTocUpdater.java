package com.study;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocxTocUpdater {

    private static final Logger log = LoggerFactory.getLogger(DocxTocUpdater.class);
    private static final int TIMEOUT_SECONDS = 60; // 转换超时时间

    // Python脚本路径 - 根据实际位置修改
    private static final String PYTHON_SCRIPT_PATH = "/opt/scripts/update_toc.py";

    /**
     * 检查LibreOffice服务是否在运行
     */
    private static boolean checkLibreOfficeService() {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "netstat", "-an", "|", "grep", "2002"
            );
            // 简化检查，实际可以尝试连接
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取Python命令（根据系统环境）
     */
    private static String getPythonCommand() {
        // 尝试多个可能的Python命令
        String[] possibleCmds = {"python3", "python", "py"};

        for (String cmd : possibleCmds) {
            try {
                ProcessBuilder pb = new ProcessBuilder(cmd, "--version");
                Process process = pb.start();
                if (process.waitFor(5, TimeUnit.SECONDS) && process.exitValue() == 0) {
                    return cmd;
                }
            } catch (Exception e) {
                // 忽略，尝试下一个
            }
        }
        return "python3"; // 默认
    }

    /**
     * 更新Word文档中的目录
     * @param wordFile 要处理的Word文件
     * @return 是否成功更新目录
     * @throws IOException 处理失败时抛出
     */
    public static boolean updateDocumentToc(File wordFile) throws IOException {
        return updateDocumentToc(wordFile, PYTHON_SCRIPT_PATH);
    }

    /**
     * 更新Word文档中的目录（指定Python脚本路径）
     * @param wordFile 要处理的Word文件
     * @param pythonScriptPath Python脚本路径
     * @return 是否成功更新目录
     * @throws IOException 处理失败时抛出
     */
    public static boolean updateDocumentToc(File wordFile, String pythonScriptPath) throws IOException {
        // 验证文件
        if (!wordFile.exists()) {
            throw new IOException("文件不存在: " + wordFile.getAbsolutePath());
        }

        if (!wordFile.getName().toLowerCase().endsWith(".docx")) {
            log.warn("文件不是docx格式: {}", wordFile.getName());
        }

        // 验证Python脚本
        File scriptFile = new File(pythonScriptPath);
        if (!scriptFile.exists()) {
            throw new IOException("Python脚本不存在: " + pythonScriptPath);
        }

        // 获取Python命令
        String pythonCmd = getPythonCommand();

        // 构建命令
        ProcessBuilder pb = new ProcessBuilder(
                pythonCmd,
                pythonScriptPath,
                wordFile.getAbsolutePath()
        );

        // 设置工作目录为脚本所在目录
        pb.directory(scriptFile.getParentFile());

        // 合并错误流和输出流
        pb.redirectErrorStream(true);

        log.info("开始更新Word目录: {}", wordFile.getAbsolutePath());
        log.debug("执行命令: {} {} {}", pythonCmd, pythonScriptPath, wordFile.getAbsolutePath());

        // 启动进程
        Process process = pb.start();

        // 读取输出
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
                log.debug("Python输出: {}", line);
            }
        }

        // 等待进程完成
        boolean finished;
        try {
            finished = process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            process.destroyForcibly();
            throw new IOException("目录更新被中断", e);
        }

        if (!finished) {
            process.destroyForcibly();
            throw new IOException("目录更新超时（" + TIMEOUT_SECONDS + "秒）");
        }

        int exitCode = process.exitValue();
        String outputStr = output.toString().trim();

        if (exitCode != 0) {
            throw new IOException("目录更新失败，退出码: " + exitCode + "，输出: " + outputStr);
        }

        // 检查输出是否包含成功信息
        boolean updated = outputStr.contains("成功更新") || outputStr.contains("updated");

        log.info("目录更新完成: {}", outputStr);
        return updated;
    }

    /**
     * 批量更新多个文档的目录
     * @param wordFiles 文件列表
     * @return 成功更新的数量
     */
    public static int batchUpdateToc(File[] wordFiles) {
        int successCount = 0;

        for (File file : wordFiles) {
            try {
                boolean updated = updateDocumentToc(file);
                if (updated) {
                    successCount++;
                    log.info("成功更新: {}", file.getName());
                } else {
                    log.warn("未找到目录: {}", file.getName());
                }
            } catch (Exception e) {
                log.error("处理失败: {} - {}", file.getName(), e.getMessage());
            }
        }

        return successCount;
    }

    /**
     * 使用示例 - 配合你的模板更新流程
     */
    public static void main(String[] args) {
        try {
            // 1. 首先用Java更新模板内容（你的业务逻辑）
            File templateFile = new File("");
            File outputFile = new File("");

            // 这里插入你的模板更新代码
            // updateTemplateContent(templateFile, outputFile, yourData);

            // 2. 然后更新目录
            boolean updated = updateDocumentToc(outputFile);

            if (updated) {
                System.out.println("目录更新成功，文档已就绪");
            } else {
                System.out.println("文档处理完成，但未找到目录");
            }

        } catch (Exception e) {
            log.error("处理失败", e);
        }
    }
}
