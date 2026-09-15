/*
package com.test;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.*;
public class Test {
    public static void main(String[] args) {
        try {
            // 读取Word文档
            FileInputStream fis = new FileInputStream("D:\\tgky\\aiptint\\storage\\docs\\targets\\mission_1\\test3\\11.doc");
            XWPFDocument document = new XWPFDocument(fis);

            // 创建PDF文档
            Document pdfDoc = new Document();
            PdfWriter.getInstance(pdfDoc, new FileOutputStream("D:\\tgky\\aiptint\\storage\\docs\\targets\\mission_1\\test3\\output.pdf"));
            pdfDoc.open();

            // 遍历Word文档段落
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                StringBuilder text = new StringBuilder();
                for (XWPFRun run : paragraph.getRuns()) {
                    text.append(run.getText(0));
                }
                // 添加段落到PDF
                pdfDoc.add(new Paragraph(text.toString()));
            }

            // 关闭资源
            pdfDoc.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
*/
