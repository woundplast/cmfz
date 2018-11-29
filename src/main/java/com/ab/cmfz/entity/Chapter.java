package com.ab.cmfz.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Chapter {
    private String id;

    private String title;

    private Double size;

    private String duration;

    private String downPath;

    private Date uploadDate;

}