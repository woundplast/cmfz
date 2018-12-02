package com.ab.cmfz.service.Impl;


import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.User;
import com.ab.cmfz.entity.UserDto;
import com.ab.cmfz.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public User selectUserByphoneNumAndPassword(String phoneNum, String password) {
        return userDao.selectUserByphoneNumAndPassword(phoneNum, password);
    }

    @Override
    public Map getCountByDays(int[] days) {
        Map map = new HashMap();
        List counts = new ArrayList();
        for (int day : days) {
            int count = userDao.getCountByDate(day);
            counts.add(count);
        }
        map.put("counts", counts);
        return map;
    }

    @Override
    public Map selectUserCountAndProvinceBySex() {
        Map map = new HashMap();
        List<UserDto> manList = userDao.selectUserCount(1);
        map.put("man", manList);
        List<UserDto> womenList = userDao.selectUserCount(0);
        map.put("women", womenList);

        return map;
    }

    @Override
    public Map getUserAll(int page, int rows) {
        int start = (page - 1) * rows;
        int pageSize = rows;
        Map map = new HashMap();
        int conut = userDao.getCount();
        List<User> userList = userDao.getUserAll(start, pageSize);
        System.out.println("---" + userList);

        map.put("rows", userList);

        map.put("total", conut);

        return map;
    }

    @Override
    public boolean exportUserData() {
        List<UserDto> userDtoList = userDao.selectUserCount(1);
        System.out.println("-" + userDtoList);

        List<String> titles = Arrays.asList("城市", "数量");
        List<User> userList = Arrays.asList(
                new User(1, "1", "1", new Date()),
                new User(2, "2", "2", new Date()),
                new User(3, "3", "3", new Date()),
                new User(4, "4", "4", new Date()),
                new User(5, "5", "5", new Date())
        );


        //1.创建一个工作簿 创建一个Excel文件对象 HSSFworkbook 工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();

        font.setColor((short) 10);
        font.setFontName("行楷");
        titleStyle.setFont(font);

        //日期
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-mm-dd");

        dateStyle.setDataFormat(format);

        //2.新建一张工作表 创建一个工作表对象 HSSFSheet
        HSSFSheet user = workbook.createSheet("user");

        user.setColumnWidth(3, 22 * 256);//列框
        //3.写入标题栏数据
        HSSFRow titleRow = user.createRow(0);
        for (int i = 0; i < titles.size(); i++) {
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(titleStyle);
        }

        //3.写入多行行数据
        //(1) 创建一行 HSSFRow
        for (int i = 0; i < userList.size(); i++) {
            HSSFRow row = user.createRow(i + 1);
            HSSFCell idCell = row.createCell(0);
            idCell.setCellValue(userList.get(i).getId());
            HSSFCell usernameCell = row.createCell(1);
            usernameCell.setCellValue(userList.get(i).getUsername());
            HSSFCell passwordCell = row.createCell(3);
            passwordCell.setCellStyle(dateStyle);
            passwordCell.setCellValue(userList.get(i).getDate());
        }

        /*for (int i = 0; i < userDtoList.size(); i++) {
            HSSFRow row = user.createRow(i + 1);
            HSSFCell idCell = row.createCell(0);
            idCell.setCellValue(userDtoList.get(i).getName());
            HSSFCell usernameCell = row.createCell(1);
            usernameCell.setCellValue(userDtoList.get(i).getValue());
            HSSFCell passwordCell = row.createCell(2);
        }*/
        //4.输出
        try {
            workbook.write(new FileOutputStream(new File("E://user10.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }




}
