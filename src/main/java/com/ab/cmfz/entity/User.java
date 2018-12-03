package com.ab.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("user")
public class User {
    @ExcelIgnore
    private Integer id;
    @ExcelIgnore
    private String phoneNum;
    @Excel(name = "姓名")
    private String username;
    @ExcelIgnore
    private String password;
    @ExcelIgnore
    private String salt;
    @ExcelIgnore
    private String dharmaname;
    @Excel(name = "城市")
    private String province;
    @ExcelIgnore
    private String city;
    @Excel(name = "性别", replace = {"男_1", "女_0"}, suffix = "生")
    private Integer sex;
    @ExcelIgnore
    private String sign;
    @ExcelIgnore
    private String headpic;
    @Excel(name = "状态", replace = {"冻结_1", "正常_0"})
    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "注册日期", exportFormat = "yyyy年MM月dd天", width = 20)
    private Date date;


    public User(Integer id, String username, String password, Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
    }

    public User(String username, Integer sex, Integer status, String province, Date date) {
        this.username = username;
        this.province = province;
        this.sex = sex;
        this.status = status;
        this.date = date;
    }
}