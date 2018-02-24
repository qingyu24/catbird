package com.winter.util;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//根据表生成实体类
public class XlsUtil {
    public static void creatClass() throws IOException {
        //        File file = new File("src/main/java/com/fngame/farm/config/MahJongConfig.xls");
        File file = new File("config/");


        File[] files = file.listFiles();

        for (File file1 : files) {
            String name = file1.getName();
            if (name.endsWith(".xlsx")) {
                File file2 = new File("src/main/java/com/winter/config/" + name.replace(".xlsx", ".java"));
                if (file2.exists()) continue;/*file2.delete()*/;
                file2.createNewFile();

                FileWriter writer = new FileWriter(file2, true);
                XlsUtil.read(writer, file1);

            }
        }

    }

  public List<String> getConfigList(){
      ArrayList<String> list = new ArrayList<>();
      File file = new File("config/");


      File[] files = file.listFiles();

      for (File file1 : files) {
          String name = file1.getName();
          if (name.endsWith(".xlsx")) {
     list.add(name.replace(".xlsx",""));

          }

      }

      return list;
  }
    public static void read(FileWriter writer1, File file) throws IOException {

        System.out.println(file.getName());

        BufferedWriter writer = new BufferedWriter(writer1);

        XSSFWorkbook wb = null;
        try {

            wb = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {

            System.out.println("运行有错");
        }
        //Excel工作表
        XSSFSheet sheet = wb.getSheetAt(0);

        //表头那一行
        XSSFRow titleRow = sheet.getRow(1);
        System.out.println(titleRow.getLastCellNum());
        XSSFCell cell = null;
        writer.write("package com.fngame.farm.config;");
        writer.newLine();
        writer.write(String.format("public class %s {", file.getName().replace(".xlsx", "")));
        writer.newLine();
        writer.flush();

        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
            cell = titleRow.getCell(i);

            try {

                writer.write("  public Integer " + cell.getStringCellValue() + ";");
            } catch (Exception e) {
                try {
                    writer.write("  public Integer " + cell.getNumericCellValue() + ";");
                } catch (Exception e1) {
                    try {
                        writer.write("  public boolean " + cell.getBooleanCellValue() + ";");
                    } catch (Exception e3) {
                        System.out.println(e3);
                    }
                }
            }
            writer.newLine();
            writer.flush();
            System.out.println("ddddddd");
        }
        writer.write("}");
        writer.close();
        writer1.close();

    }


    public static void getloader() throws IOException {
        //        File file = new File("src/main/java/com/fngame/farm/config/MahJongConfig.xls");
        File file = new File("src/main/resources/config/");


        File[] files = file.listFiles();

        for (File file1 : files) {
            String name = file1.getName();
            if (name.endsWith(".xlsx")) {


                XlsUtil.getlist(name.replace(".xlsx", "loader"));

            }
        }

    }

    public static void getlist(String file) {
        String replace = file.replace(".java", "");
        String s = "  private static   ArrayList<%managerExample> %sloader=new ArrayList();";
        String s1 = String.format("  loades.put(\"%managerExample\", %managerExample);", file.replace("loader", ""), file);

        String format = String.format(s, replace, replace, replace);
        System.out.println(s1);

    }
//生成configManager
    public void readManager() throws Exception {
        List<String> configList = this.getConfigList();
        File file = new File("src/main/resources/config/managerExample");
       File files= new File("src/main/java/com/fngame/farm/manager/ConfigLoaderManager.java");
       if(files.exists())files.delete();
        files.createNewFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter BufferedWriter=new BufferedWriter(new FileWriter(files,true));
        String s = null;
        while ((s=bufferedReader.readLine()) != null) {

            String trim = s.trim();
            if (s.equals("add1")) {
                System.out.println("---------------");
                s = bufferedReader.readLine();
                for (String s1 : configList) {
                    BufferedWriter.write(String.format(s,s1,s1,s1));
                    BufferedWriter.newLine();
                    BufferedWriter.flush();
                    System.out.println(String.format(s,s1,s1,s1));
                }

            }else if(trim.equals("add2")){
                System.out.println("_+++++++++++++++++++");
                s=bufferedReader.readLine();
                for (String s1 : configList) {

                    BufferedWriter.write(String.format(s,s1,s1));
                    BufferedWriter.newLine();
                    BufferedWriter.flush();
                    System.out.println(String.format(s,s1,s1,s1));
                }
            }

                else{
                BufferedWriter.write(s);

            }
            BufferedWriter.newLine();
            BufferedWriter.flush();

        }

    }

}