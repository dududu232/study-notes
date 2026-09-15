package com.test.testHtmlToPdf;

//import com.ironsoftware.ironpdf.PdfDocument;

import java.io.*;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) throws IOException {
//        String htmlString = "<h1>My First PDF File<h1/><p> This is sample pdf file</p>";
//        PdfDocument myPdf = PdfDocument.renderHtmlAsPdf(htmlString);
//
//// Save the PdfDocument to a file
//        try {
//            myPdf.saveAs(Paths.get("myPDF.pdf") );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        long startTime = System.currentTimeMillis();
        //       html文件所在相对路径
        String htmlFile = "D:\\study-notes\\study_10000_test\\src\\main\\resources\\static/html/testCountdown.html";
        //       pdf文件存储相对路径
        String pdfFile = "D:\\study-notes\\study_10000_test\\src\\main\\resources\\static/html/x6.pdf";
        //        自定义水印
        String waterMarkText =  "";
        InputStream inputStream = new FileInputStream(htmlFile);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        //微软雅黑在windows系统里的位置如下，linux系统直接拷贝该文件放在linux目录下即可
        //        String fontPath = "src/main/resources/font/STHeiti Light.ttc,0";
        String fontPath = "D:\\study-notes\\study_10000_test\\src\\main\\resources\\msyhl.ttc,0";
        HtmlToPdfUtils.convertToPdf(inputStream, waterMarkText, fontPath, outputStream);
        System.out.println("转换结束，耗时："+(System.currentTimeMillis()-startTime)+"ms");
//        log.info("转换结束，耗时：{}ms",System.currentTimeMillis()-startTime);
    }
}
