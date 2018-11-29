package com.ab.cmfz.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Album {
    private Integer id;

    private String title;

    private String coverImg;

    private Integer acount;

    private Integer score;

    private String author;

    private String brodCast;

    private String brief;

    private Date publishDate;

    List<Chapter> children;


}