package com.ab.cmfz.controller;

import com.ab.cmfz.entity.Slide;
import com.ab.cmfz.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
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

    @RequestMapping("/updateSlideByidAndstatus")
    public @ResponseBody
    boolean dpdate(int id, int status) {
        try {
            slideService.updateSlideByidAndstatus(id, status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/addSilde")
    public @ResponseBody
    boolean addSilde(Slide slide, HttpServletRequest request, MultipartFile img) {

        //String realPath = request.getSession().getServletContext().getRealPath("/shouye");

        //2.file对象
        //File file = new File(realPath+"\\"+uploadFile.getOriginalFilename());
        //3.写入
        //uploadFile.transferTo(file);

        try {
            String fileName = img.getOriginalFilename();
            //获取新文件名
            String newFileName = new Date().getTime() + "_" + fileName;
            String realPath = request.getSession().getServletContext().getRealPath("/shouye");
            File file = new File(realPath + "\\" + newFileName);
            img.transferTo(file);
            slide.setImgPath(newFileName);
            slideService.addSilde(slide);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/deleteSlide")
    public @ResponseBody
    boolean deleteSlide(int id) {
        System.out.println(id + "-----");
        try {
            slideService.deleteSlide(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
