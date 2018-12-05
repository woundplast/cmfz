package com.ab.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artical {
    private String id;
    private String title;
    private String author;
    private String content;
    private String date;
    private String url;
}
