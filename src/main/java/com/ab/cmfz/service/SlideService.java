package com.ab.cmfz.service;

import com.ab.cmfz.entity.Slide;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SlideService {

    Map getSileByPage(int page, int rows);

    void updateSlideByidAndstatus(@Param("id") int id, @Param("status") int status);

    void addSilde(Slide slide);

    void deleteSlide(int id);

}
