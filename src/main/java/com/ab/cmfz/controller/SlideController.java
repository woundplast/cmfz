package com.ab.cmfz.controller;

import com.ab.cmfz.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping
public class SlideController {

    @Autowired
    SlideService slideService;

    @RequestMapping("/getSlideByPage")
    public @ResponseBody
    Map getSlideByPage(int page, int rows) {
        System.out.println("--------" + page + rows);
        Map map = slideService.getSileByPage(page, rows);
        return map;
    }
}
