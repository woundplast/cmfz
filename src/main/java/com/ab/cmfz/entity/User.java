package com.ab.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String phoneNum;

    private String username;

    private String password;

    private String salt;

    private String dharmaname;

    private String province;

    private String city;

    private Integer sex;

    private String sign;

    private String headpic;

    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date date;


    public User(Integer id, String username, String password, Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
    }

    public User(String username, String province, Integer sex, Integer status, Date date) {
        this.username = username;
        this.province = province;
        this.sex = sex;
        this.status = status;
        this.date = date;
    }
}