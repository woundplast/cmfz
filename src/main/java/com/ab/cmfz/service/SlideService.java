package com.ab.cmfz.service;

import com.ab.cmfz.entity.Slide;

import java.util.Map;

public interface SlideService {

    Map getSileByPage(int page, int rows);

    void updateSlideByidAndstatus(int id, int status);

    void addSilde(Slide slide);

    void deleteSlide(int id);

}
