package com.study.controller;


import com.study.util.WordToPdfUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/04/21/11:00
 * @Description:
 */
@RestController
@RequestMapping("/file-converter")
@Slf4j
public class FileConversionController {

    @PostMapping("/wordToPdf")
    public ResponseEntity<byte[]> wordToPdf(MultipartFile file){


        // 1. 参数校验
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || (!originalFilename.endsWith(".docx") && !originalFilename.endsWith(".doc"))) {
            return ResponseEntity.badRequest().build();
        }

        try {
            // 2. 保存临时文件
            File tempFile = File.createTempFile("word_", originalFilename);
            file.transferTo(tempFile);

            // 3. 调用转换工具
            File pdfFile = WordToPdfUtil.convert(tempFile);

            // 4. 读取PDF并返回
            byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath());

            // 5. 清理临时文件
            tempFile.delete();
            pdfFile.delete();

            // 6. 返回响应
            String pdfName = originalFilename.replaceAll("\\.[^.]+$", ".pdf");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfName + "\"")
                    .body(pdfBytes);

        } catch (IOException e) {
            log.error("转换失败: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
