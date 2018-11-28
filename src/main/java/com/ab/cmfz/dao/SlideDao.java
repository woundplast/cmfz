package com.ab.cmfz.dao;


import com.ab.cmfz.entity.Slide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SlideDao {
    List<Slide> getSlideByPage(@Param("start") int start, @Param("end") int end);

    int getConut();


}