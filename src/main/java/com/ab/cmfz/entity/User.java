package com.ab.cmfz.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String phonenum;

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

    private Date date;

}