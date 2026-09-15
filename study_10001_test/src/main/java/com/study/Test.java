package com.study;


import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.core.io.Resource;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/25/18:57
 * @Description:
 */
public class Test {
     /*public static void main(String[] args) throws IOException {
       // 创建表格（先创建行数，后续添加数据）

        XWPFDocument doc = new XWPFDocument(new FileInputStream("D:\\tgky\\aiptint\\service\\src\\main\\resources\\templates\\mission-final-check-report-template.docx"));
        XWPFTable table = doc.createTable(2, 4);  // 先创建2行4列的表头

        // ========== 第一行表头 ==========
        XWPFTableRow row1 = table.getRow(0);

        // 第1列：风险领域
        XWPFTableCell cell1_1 = row1.getCell(0);
        setCellText(cell1_1, "风险领域", true, true,"FFFFFF");
        setCellBackground(cell1_1, "002060");  // 设置灰色背景

        // 第2列：剩余风险等级（起始列）
        XWPFTableCell cell1_2 = row1.getCell(1);
        setCellText(cell1_2, "剩余风险等级", true, true,"FFFFFF");
        setCellBackground(cell1_2, "002060");

        // 第3列：占位列（与第2列合并）
        XWPFTableCell cell1_3 = row1.getCell(2);
        setCellBackground(cell1_3, "002060");

        // 第4列：剩余风险总数
        XWPFTableCell cell1_4 = row1.getCell(3);
        setCellText(cell1_4, "剩余风险总数", true, true,"FFFFFF");
        setCellBackground(cell1_4, "002060");

        // 合并第2、3列（水平合并）
        mergeCellsHorizontally(table, 0, 1, 2);

        // ========== 第二行表头 ==========
        XWPFTableRow row2 = table.getRow(1);

        // 第1列：与第一行合并
        XWPFTableCell cell2_1 = row2.getCell(0);
        // 第2列：高
        XWPFTableCell cell2_2 = row2.getCell(1);
        setCellText(cell2_2, "高", true, true);
        setCellBackground(cell2_2, "FF0000");
        // 第3列：中
        XWPFTableCell cell2_3 = row2.getCell(2);
        setCellText(cell2_3, "中", true, true);
        setCellBackground(cell2_3, "FFC000");
        // 第4列：与第一行合并
        XWPFTableCell cell2_4 = row2.getCell(3);

        // 垂直合并：第1列（风险领域）
        mergeCellsVertically(table, 0, 0, 1);
        // 垂直合并：第4列（剩余风险总数）
        mergeCellsVertically(table, 3, 0, 1);


        // ========== 添加数据行 ==========
        String[][] data = {
                {"信息科技治理", "2", "3", "5"},
                {"信息科技风险管理", "1", "4", "5"},
                {"信息安全", "3", "5", "8"},
        };

        for (String[] rowData : data) {
            XWPFTableRow dataRow = table.createRow();
            for (int i = 0; i < rowData.length; i++) {
                XWPFTableCell cell;
                if (i == 0) {
                    cell = dataRow.getCell(0);
                } else {
                    // 确保有足够的列
                    while (dataRow.getTableCells().size() <= i) {
                        dataRow.addNewTableCell();
                    }
                    cell = dataRow.getCell(i);
                }
                setCellText(cell, rowData[i], false, true);
            }
        }

        // ========== 添加总计行 ==========
        XWPFTableRow totalRow = table.createRow();
        XWPFTableCell totalCell1 = totalRow.getCell(0);
        setCellText(totalCell1, "总计", true, false);
        setCellCenter(totalCell1);
        // 确保有足够的列
        while (totalRow.getTableCells().size() < 4) {
            totalRow.addNewTableCell();
        }
        setCellText(totalRow.getCell(1), "12", true, true);
        setCellText(totalRow.getCell(2), "29", true, true);
        setCellText(totalRow.getCell(3), "41", true, true);

        // 设置表格边框
        setTableBorder(table, STBorder.SINGLE, 1, "000000");



        for (int i = 0; i < table.getNumberOfRows(); i++) {
            XWPFTableRow row = table.getRow(i);
            CTTrPr trPr = getOrCreateTrPr(row);
            // POI 5.3.0 中使用 getTrHeight() 和 setTrHeight() 方法
            CTHeight ctHeight = trPr.getTrHeight();
            if (ctHeight == null) {
                ctHeight = trPr.addNewTrHeight();
            }
            ctHeight.setVal(BigInteger.valueOf(height));
            ctHeight.setHRule(rule);
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\output.docx");
        doc.write(fileOutputStream);
    }


    *//**
     * 获取或创建行的属性对象
     *//*
    private static CTTrPr getOrCreateTrPr(XWPFTableRow row) {
        CTRow ctRow = row.getCtRow();
        CTTrPr trPr = ctRow.getTrPr();
        if (trPr == null) {
            trPr = ctRow.addNewTrPr();
        }
        return trPr;
    }


    // ==================== 1. 文档和表格基础操作 ====================

    *//**
     * 创建表格的几种方式
     *//*
    public void createTableExample(XWPFDocument doc) {
        // 方式1：创建空表格（需要手动添加行和列）
        XWPFTable table1 = doc.createTable();

        // 方式2：创建指定行数的表格（列数可以后续添加）
        XWPFTable table2 = doc.createTable(5, 0);  // 5行，0列

        // 方式3：创建指定行列的表格
        XWPFTable table3 = doc.createTable(5, 4);  // 5行4列

        // 获取文档中所有表格
        List<XWPFTable> allTables = doc.getTables();

        // 获取最后一个表格
        XWPFTable lastTable = allTables.get(allTables.size() - 1);
    }

    // ==================== 2. 行操作 ====================

    *//**
     * 设置单元格文字居中（水平居中 + 垂直居中）
     * @param cell 目标单元格
     *//*
    private static void setCellCenter(XWPFTableCell cell) {
        // 1. 设置水平居中
        XWPFParagraph para;
        if (cell.getParagraphs().size() > 0) {
            para = cell.getParagraphs().get(0);
        } else {
            para = cell.addParagraph();
        }
        para.setAlignment(ParagraphAlignment.CENTER);

        // 2. 设置垂直居中
        CTTcPr tcPr = getOrCreateTcPr(cell);
        if (tcPr.isSetVAlign()) {
            tcPr.getVAlign().setVal(STVerticalJc.CENTER);
        } else {
            tcPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        }
    }

    // ==================== 3. 单元格操作 ====================

    *//**
     * 单元格操作示例
     *//*
    public void cellOperation(XWPFTableRow row) {
        // 获取指定列单元格（索引从0开始）
        XWPFTableCell cell = row.getCell(0);

        // 创建新单元格（添加到行末尾）
        XWPFTableCell newCell = row.createCell();

        // 获取单元格文本（合并所有段落文本）
        String text = cell.getText();

        // 清空单元格所有内容
        clearCellContent(cell);

        // 设置单元格宽度
        setCellWidth(cell, 2000);  // 宽度2000，单位1/20磅
    }

    *//**
     * 清空单元格内容
     * @param cell 目标单元格
     *//*
    private static void clearCellContent(XWPFTableCell cell) {
        // 循环删除所有段落
        while (cell.getParagraphs().size() > 0) {
            cell.removeParagraph(0);
        }
    }

    *//**
     * 设置单元格宽度
     * @param cell 目标单元格
     * @param width 宽度值（单位：1/20磅，1磅=20）
     *//*
    private static void setCellWidth(XWPFTableCell cell, int width) {
        // 获取或创建单元格属性对象
        CTTcPr tcPr = cell.getCTTc().getTcPr();
        if (tcPr == null) {
            tcPr = cell.getCTTc().addNewTcPr();
        }

        // 获取或创建宽度对象
        CTTblWidth cellWidth = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
        cellWidth.setW(BigInteger.valueOf(width));
        cellWidth.setType(STTblWidth.DXA);  // DXA：单位1/20磅
    }

    // ==================== 4. 段落和文本操作 ====================

    *//**
     * 设置单元格文本（简单版本）
     * @param cell 目标单元格
     * @param text 文本内容
     * @param bold 是否加粗
     * @param center 是否居中
     *//*
    private static void setCellText(XWPFTableCell cell, String text, boolean bold, boolean center,String colorHex) {
        // 清空原有内容
        while (cell.getParagraphs().size() > 0) {
            cell.removeParagraph(0);
        }

        // 创建新段落
        XWPFParagraph para = cell.addParagraph();

        // 设置对齐方式
        if (center) {
            para.setAlignment(ParagraphAlignment.CENTER);
        } else {
            para.setAlignment(ParagraphAlignment.LEFT);
        }

        // 创建文本运行
        XWPFRun run = para.createRun();
        run.setText(text);
        run.setBold(bold);
        if (colorHex!=null){
            run.setColor(colorHex);
        }
        setCellCenter(cell);
    }

    private static void setCellText(XWPFTableCell cell, String text, boolean bold, boolean center){
        setCellText(cell, text, bold, center,null);
    }
    *//**
     * 设置单元格文本（完整版本，支持更多格式）
     * @param cell 目标单元格
     * @param text 文本内容
     * @param bold 是否加粗
     * @param italic 是否斜体
     * @param fontSize 字号（磅）
     * @param fontFamily 字体名称
     * @param color 颜色（如 "FF0000" 红色）
     * @param underline 下划线类型
     * @param alignment 对齐方式
     *//*
    private static void setCellTextFull(
            XWPFTableCell cell,
            String text,
            boolean bold,
            boolean italic,
            int fontSize,
            String fontFamily,
            String color,
            UnderlinePatterns underline,
            ParagraphAlignment alignment) {

        // 清空原有内容
        while (cell.getParagraphs().size() > 0) {
            cell.removeParagraph(0);
        }

        // 创建段落
        XWPFParagraph para = cell.addParagraph();
        para.setAlignment(alignment);

        // 创建文本运行
        XWPFRun run = para.createRun();
        run.setText(text);
        run.setBold(bold);
        run.setItalic(italic);
        run.setFontSize(fontSize);
        if (fontFamily != null) {
            run.setFontFamily(fontFamily);
        }
        if (color != null) {
            run.setColor(color);
        }
        if (underline != null) {
            run.setUnderline(underline);
        }
    }

    // ==================== 5. 单元格合并操作 ====================

    *//**
     * 水平合并单元格
     * 将指定行的 startCol 到 endCol 列合并为一个单元格
     *
     * @param table 表格对象
     * @param row 行索引（从0开始）
     * @param startCol 起始列索引（从0开始）
     * @param endCol 结束列索引（从0开始）
     *
     * 示例：合并第0行的第1列到第3列
     * mergeCellsHorizontally(table, 0, 1, 3);
     *//*
    private static void mergeCellsHorizontally(XWPFTable table, int row, int startCol, int endCol) {
        XWPFTableRow tableRow = table.getRow(row);

        for (int i = startCol; i <= endCol; i++) {
            XWPFTableCell cell = tableRow.getCell(i);
            CTTcPr tcPr = getOrCreateTcPr(cell);

            if (i == startCol) {
                // 起始单元格：标记为合并开始
                tcPr.addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // 后续单元格：标记为合并继续
                tcPr.addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    *//**
     * 垂直合并单元格
     * 将指定列的 startRow 到 endRow 行合并为一个单元格
     *
     * @param table 表格对象
     * @param col 列索引（从0开始）
     * @param startRow 起始行索引（从0开始）
     * @param endRow 结束行索引（从0开始）
     *
     * 示例：合并第0列的第0行到第1行
     * mergeCellsVertically(table, 0, 0, 1);
     *//*
    private static void mergeCellsVertically(XWPFTable table, int col, int startRow, int endRow) {
        for (int i = startRow; i <= endRow; i++) {
            XWPFTableRow tableRow = table.getRow(i);
            XWPFTableCell cell = tableRow.getCell(col);
            CTTcPr tcPr = getOrCreateTcPr(cell);

            if (i == startRow) {
                // 起始单元格：标记为合并开始
                tcPr.addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // 后续单元格：标记为合并继续
                tcPr.addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    *//**
     * 获取或创建单元格属性对象
     *//*
    private static CTTcPr getOrCreateTcPr(XWPFTableCell cell) {
        CTTc ctTc = cell.getCTTc();
        CTTcPr tcPr = ctTc.getTcPr();
        if (tcPr == null) {
            tcPr = ctTc.addNewTcPr();
        }
        return tcPr;
    }

    // ==================== 6. 单元格颜色/背景色设置 ====================

    *//**
     * 设置单元格背景色
     *
     * @param cell 目标单元格
     * @param colorHex 颜色十六进制值，如 "FF0000" 红色，"00FF00" 绿色，"0000FF" 蓝色
     *                 注意：不要带#号，直接6位十六进制
     *
     * 示例：设置单元格背景色为浅灰色
     * setCellBackground(cell, "EEEEEE");
     *//*
    private static void setCellBackground(XWPFTableCell cell, String colorHex) {
        CTTcPr tcPr = getOrCreateTcPr(cell);

        // 创建或获取阴影/背景对象
        CTShd shd = tcPr.isSetShd() ? tcPr.getShd() : tcPr.addNewShd();

        // 设置填充颜色
        shd.setFill(colorHex);

        // 设置填充模式（可选）
        shd.setVal(STShd.CLEAR);
    }

    *//**
     * 设置单元格边框
     *
     * @param cell 目标单元格
     * @param borderType 边框类型
     * @param borderSize 边框大小
     * @param colorHex 边框颜色
     *//*
    private static void setCellBorder(XWPFTableCell cell, STBorder.Enum borderType,
                                      int borderSize, String colorHex) {
        *//*CTTcPr tcPr = getOrCreateTcPr(cell);

        // 设置上边框
        CTBorder top = tcPr.isSetTop() ? tcPr.getTop() : tcPr.addNewTop();
        top.setVal(borderType);
        top.setSz(BigInteger.valueOf(borderSize));
        top.setColor(colorHex);

        // 设置下边框
        CTBorder bottom = tcPr.isSetBottom() ? tcPr.getBottom() : tcPr.addNewBottom();
        bottom.setVal(borderType);
        bottom.setSz(BigInteger.valueOf(borderSize));
        bottom.setColor(colorHex);

        // 设置左边框
        CTBorder left = tcPr.isSetLeft() ? tcPr.getLeft() : tcPr.addNewLeft();
        left.setVal(borderType);
        left.setSz(BigInteger.valueOf(borderSize));
        left.setColor(colorHex);

        // 设置右边框
        CTBorder right = tcPr.isSetRight() ? tcPr.getRight() : tcPr.addNewRight();
        right.setVal(borderType);
        right.setSz(BigInteger.valueOf(borderSize));
        right.setColor(colorHex);*//*
    }

    // ==================== 7. 表格样式设置 ====================

    *//**
     * 设置表格整体边框
     *
     * @param table 表格对象
     * @param borderType 边框类型
     * @param borderSize 边框大小
     * @param colorHex 边框颜色
     *//*
    private static void setTableBorder(XWPFTable table, STBorder.Enum borderType,
                                       int borderSize, String colorHex) {
        CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();

        // 上边框
        CTBorder top = borders.addNewTop();
        top.setVal(borderType);
        top.setSz(BigInteger.valueOf(borderSize));
        top.setColor(colorHex);

        // 下边框
        CTBorder bottom = borders.addNewBottom();
        bottom.setVal(borderType);
        bottom.setSz(BigInteger.valueOf(borderSize));
        bottom.setColor(colorHex);

        // 左边框
        CTBorder left = borders.addNewLeft();
        left.setVal(borderType);
        left.setSz(BigInteger.valueOf(borderSize));
        left.setColor(colorHex);

        // 右边框
        CTBorder right = borders.addNewRight();
        right.setVal(borderType);
        right.setSz(BigInteger.valueOf(borderSize));
        right.setColor(colorHex);

        // 内部水平线
        CTBorder insideH = borders.addNewInsideH();
        insideH.setVal(borderType);
        insideH.setSz(BigInteger.valueOf(borderSize));
        insideH.setColor(colorHex);

        // 内部垂直线
        CTBorder insideV = borders.addNewInsideV();
        insideV.setVal(borderType);
        insideV.setSz(BigInteger.valueOf(borderSize));
        insideV.setColor(colorHex);
    }

    *//**
     * 设置表格宽度
     * @param table 表格对象
     * @param width 宽度（单位：1/20磅）
     *//*
    private static void setTableWidth(XWPFTable table, int width) {
        CTTblWidth tblWidth = table.getCTTbl().getTblPr().addNewTblW();
        tblWidth.setW(BigInteger.valueOf(width));
        tblWidth.setType(STTblWidth.DXA);
    }*/

}
