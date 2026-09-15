//package com.study;
//
//
//
//import org.apache.poi.xwpf.usermodel.*;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
//
//import java.io.*;
//import java.math.BigInteger;
//
//public class RefreshTOC {
//
//    public static void main(String[] args) {
//        if (args.length < 2) {
//            System.out.println("Usage: UpdateWordTOC <input.docx> <output.docx>");
//            return;
//        }
//
//        try (FileInputStream fis = new FileInputStream(args[0]);
//             XWPFDocument doc = new XWPFDocument(fis)) {
//
//            // 更新目录域
//            updateTOCField(doc);
//
//            // 更新页码域
//            updatePageFields(doc);
//
//            // 保存文档
//            try (FileOutputStream fos = new FileOutputStream(args[1])) {
//                doc.write(fos);
//            }
//            System.out.println("目录页码更新成功！");
//        } catch (IOException e) {
//            System.err.println("处理文档时出错: " + e.getMessage());
//        }
//    }
//
//    //传入文档的路径
//    private void updateTOC(String path){
//        ActiveXComponent wordApp = new ActiveXComponent("Word.Application");
//        try {
//            wordApp.setProperty("Visible", new Variant(false));
//            Dispatch wordDocuments = wordApp.getProperty("Documents").toDispatch();
//            Dispatch document = Dispatch.call(wordDocuments, "Open", path).toDispatch();
//
//            // 获取目录对象
//            Dispatch tableOfContents = Dispatch.call(document, "TablesOfContents", 1).toDispatch();
//
//            //设置目录级别范围
//            Dispatch.put(tableOfContents,"LevelRange","1-2");
//
//            // 刷新目录
//            Dispatch.call(tableOfContents, "Update");
//
//            // 保存文档
//            Dispatch.call(document, "Save");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            Dispatch.call(wordApp, "Quit", new Variant(false));
//        }
//    }
//
//}
