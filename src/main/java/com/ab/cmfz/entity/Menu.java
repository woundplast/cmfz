package com.ab.cmfz.entity;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;

    private String title;

    private String iconcls;

    private Integer parentId;

    private String url;

    List<Menu> menus;
}