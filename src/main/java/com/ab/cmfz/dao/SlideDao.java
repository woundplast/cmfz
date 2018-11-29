package com.ab.cmfz.dao;


import com.ab.cmfz.entity.Slide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SlideDao {
    List<Slide> getSlideByPage(@Param("start") int start, @Param("end") int end);
    int getConut();

    void updateSlideByidAndstatus(@Param("id") int id, @Param("status") int status);

    void addSilde(Slide slide);

    void deleteSlide(int id);


}