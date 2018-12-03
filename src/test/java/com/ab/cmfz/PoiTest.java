//package com.ab.cmfz;
//
//import com.ab.cmfz.dao.UserDao;
//import com.ab.cmfz.entity.User;
//import com.ab.cmfz.entity.UserDto;
//import org.apache.poi.hssf.usermodel.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PoiTest extends TmallApplicationTests {
//
//    /**
//     * 一行一列
//     */
//    @Test
//    public void test1() {
//        //1.创建一个工作簿 创建一个Excel文件对象 HSSFworkbook 工作簿
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        //2.新建一张工作表 创建一个工作表对象 HSSFSheet
//        HSSFSheet user = workbook.createSheet("user");
//
//        //3.写入一行数据
//        //(1) 创建一行 HSSFRow
//        HSSFRow row = user.createRow(0);
//        //(2) 创建一个单元格 HSSFCell
//        HSSFCell cell = row.createCell(0);
//        //(3) 在单元格中写入数据
//        cell.setCellValue("编号");
//
//        //4.输出
//        try {
//            workbook.write(new FileOutputStream(new File("E://user.xls")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 一行三列
//     */
//    @Test
//    public void test2() {
//        List<String> titles = Arrays.asList("编号", "用户名", "密码");
//
//        //1.创建一个工作簿 创建一个Excel文件对象 HSSFworkbook 工作簿
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        //2.新建一张工作表 创建一个工作表对象 HSSFSheet
//        HSSFSheet user = workbook.createSheet("user2");
//
//        //3.写入一行数据
//        //(1) 创建一行 HSSFRow
//        HSSFRow row = user.createRow(0);
//        //(2) 创建一个单元格 HSSFCell
//        for (int i = 0; i < titles.size(); i++) {
//            HSSFCell cell = row.createCell(i);
//            //(3) 在单元格中写入数据
//            cell.setCellValue(titles.get(i));
//
//        }
//
//        //4.输出
//        try {
//            workbook.write(new FileOutputStream(new File("E://user2.xls")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Autowired
//    UserDao userDao;
//
//    /**
//     * 设置标题栏  填充数据
//     */
//    @Test
//    public void test3() {
//        List<UserDto> userDtoList = userDao.selectUserCount(1);
//        System.out.println("-" + userDtoList);
//
//        List<String> titles = Arrays.asList("城市", "数量");
//        List<User> userList = Arrays.asList(
//                new User(1, "1", "1", new Date()),
//                new User(2, "2", "2", new Date()),
//                new User(3, "3", "3", new Date()),
//                new User(4, "4", "4", new Date()),
//                new User(5, "5", "5", new Date())
//        );
//
//
//        //1.创建一个工作簿 创建一个Excel文件对象 HSSFworkbook 工作簿
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFCellStyle titleStyle = workbook.createCellStyle();
//        HSSFFont font = workbook.createFont();
//
//        font.setColor((short) 10);
//        font.setFontName("行楷");
//        titleStyle.setFont(font);
//
//        //日期
//        HSSFCellStyle dateStyle = workbook.createCellStyle();
//        HSSFDataFormat dataFormat = workbook.createDataFormat();
//        short format = dataFormat.getFormat("yyyy-mm-dd");
//
//        dateStyle.setDataFormat(format);
//
//        //2.新建一张工作表 创建一个工作表对象 HSSFSheet
//        HSSFSheet user = workbook.createSheet("user");
//
//        user.setColumnWidth(3, 22 * 256);//列框
//        //3.写入标题栏数据
//        HSSFRow titleRow = user.createRow(0);
//        for (int i = 0; i < titles.size(); i++) {
//            HSSFCell cell = titleRow.createCell(i);
//            cell.setCellValue(titles.get(i));
//            cell.setCellStyle(titleStyle);
//        }
//
//        //3.写入多行行数据
//        //(1) 创建一行 HSSFRow
//        for (int i = 0; i < userList.size(); i++) {
//            HSSFRow row = user.createRow(i + 1);
//            HSSFCell idCell = row.createCell(0);
//            idCell.setCellValue(userList.get(i).getId());
//            HSSFCell usernameCell = row.createCell(1);
//            usernameCell.setCellValue(userList.get(i).getUsername());
//            HSSFCell passwordCell = row.createCell(3);
//            passwordCell.setCellStyle(dateStyle);
//            passwordCell.setCellValue(userList.get(i).getDate());
//        }
//
//        /*for (int i = 0; i < userDtoList.size(); i++) {
//            HSSFRow row = user.createRow(i + 1);
//            HSSFCell idCell = row.createCell(0);
//            idCell.setCellValue(userDtoList.get(i).getName());
//            HSSFCell usernameCell = row.createCell(1);
//            usernameCell.setCellValue(userDtoList.get(i).getValue());
//            HSSFCell passwordCell = row.createCell(2);
//        }*/
//        //4.输出
//        try {
//            workbook.write(new FileOutputStream(new File("E://user10.xls")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /***
//     * 导入  读取本地的Excel文件 解析其中的数据
//     * 封装成对象 （可做可不做）
//     */
//
//    @Test
//    public void Test6() throws Exception {
//        FileInputStream fileInputStream = new FileInputStream(new File("E://user3.xls"));
//        //1.接受流中数据 获取流中工作对象
//        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
//        //2.获取工作表
//        HSSFSheet sheet = workbook.getSheetAt(0);
//        //3.获取数据 获取行
//
//
//        int lastNum = sheet.getLastRowNum();
//        for (int i = 0; i < lastNum; i++) {
//            HSSFRow row = sheet.getRow(i + 1);
//
//            HSSFCell idcell = row.getCell(0);
//            double id = idcell.getNumericCellValue();
//
//            HSSFCell Usercell = row.getCell(1);
//            String username = Usercell.getStringCellValue();
//
//            HSSFCell passwordCell = row.getCell(2);
//            String password = passwordCell.getStringCellValue();
//
//            System.out.println("*-" + id + "+" + username + "++" + password);
//        }
//
//
//    }
//
//
//}
