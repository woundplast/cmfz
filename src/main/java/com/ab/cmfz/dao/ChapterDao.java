package com.ab.cmfz.dao;

import com.ab.cmfz.entity.Chapter;

public interface ChapterDao {
    int insert(Chapter record);

    int insertSelective(Chapter record);
}