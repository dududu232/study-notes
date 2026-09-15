//package com.study;
//
//
//
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.xmlbeans.XmlObject;
//import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//
//
//
//public class WordUpdateToc {
//
//    public static void main(String[] args) {
//        // 替换为你的文件路径
//        String inputPath = "C:/test/input.docx";
//        String outputPath = "C:/test/output.docx";
//
//        try {
//            updateWordTOC(inputPath, outputPath);
//            System.out.println("✅ 目录更新成功！打开文件自动刷新");
//        } catch (Exception e) {
//            System.err.println("❌ 更新失败：" + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 核心方法：更新 Word/WPS 目录
//     */
//    public static void updateWordTOC(String inputPath, String outputPath) throws Exception {
//        try (FileInputStream fis = new FileInputStream(inputPath);
//             XWPFDocument doc = new XWPFDocument(fis);
//             FileOutputStream fos = new FileOutputStream(outputPath)) {
//
//            // 1. 遍历所有段落，找到 TOC 域并标记为需要更新
//            for (XWPFParagraph para : doc.getParagraphs()) {
//                CTP ctPara = para.getCTP();
//                // 方式1：处理段落级别的 SdtBlock（WPS 目录）
//                if (ctPara.getSdtList() != null) {
//                    for (CTSdtBlock sdtBlock : ctPara.getSdtList()) {
//                        markTOCFieldDirty(sdtBlock.getSdtContent());
//                    }
//                }
//
//                // 方式2：遍历段落所有 XML 节点，定位 TOC 域（绕过 CTR.getSdtList()）
//                XmlObject[] xmlObjects = ctPara.selectPath(
//                        "declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' " +
//                                ".//w:sdt//w:fldSimple[contains(@w:instr, 'TOC')]"
//                );
//                for (XmlObject xmlObj : xmlObjects) {
//                    if (xmlObj instanceof CTSimpleField) {
//                        CTSimpleField tocField = (CTSimpleField) xmlObj;
//                        tocField.setDirty(STOnOff.ON); // 标记更新
//                    }
//                }
//            }
//
//            // 2. 开启文档打开时自动更新域（无需手动确认）
//            enableAutoUpdateFields(doc);
//
//            // 3. 保存文档
//            doc.write(fos);
//        }
//    }
//
//    /**
//     * 标记 SdtContent 中的 TOC 域为需要更新
//     */
//    private static void markTOCFieldDirty(CTSdtContent sdtContent) {
//        if (sdtContent == null || sdtContent.getFldSimpleList() == null) {
//            return;
//        }
//        for (CTSimpleField field : sdtContent.getFldSimpleList()) {
//            String instr = field.getInstr();
//            if (instr != null && instr.trim().toUpperCase().contains("TOC")) {
//                field.setDirty(STOnOff.ON);
//            }
//        }
//    }
//
//    /**
//     * 开启文档自动更新域
//     */
//    private static void enableAutoUpdateFields(XWPFDocument doc) {
//        CTDocument1 ctDoc = doc.getDocument();
//        CTSettings settings = ctDoc.getSettings() == null ? ctDoc.addNewSettings() : ctDoc.getSettings();
//
//        CTOnOff updateFields = settings.getUpdateFields() == null ? settings.addNewUpdateFields() : settings.getUpdateFields();
//        updateFields.setVal(STOnOff.ON);
//    }
//}
//
