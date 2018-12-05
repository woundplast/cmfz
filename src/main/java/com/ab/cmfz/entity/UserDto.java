package com.ab.cmfz.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    //省份
    private String name;
    private Integer value;
    private List<User> users;

}
