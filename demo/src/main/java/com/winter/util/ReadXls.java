/*
package com.winter.util;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;

public class ReadXls {
    public void testRead() throws Exception {
        //Excel文件
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:web-info.xls")));
        //Excel工作表
        HSSFSheet sheet = wb.getSheetAt(0);

        //表头那一行
        HSSFRow titleRow = sheet.getRow(0);

        //表头那个单元格
        HSSFCell titleCell = titleRow.getCell(0);

        String title = titleCell.getStringCellValue();

        System.out.println("标题是："+title);
    }
}
*/
