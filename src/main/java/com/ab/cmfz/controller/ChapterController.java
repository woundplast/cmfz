package com.ab.cmfz.controller;

import com.ab.cmfz.entity.Chapter;
import com.ab.cmfz.service.ChapterService;
import com.ab.cmfz.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Controller
@RequestMapping
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping("/addChapter")
    public @ResponseBody
    boolean addChapter(Chapter chapter, HttpServletRequest request, MultipartFile img, String ctitle) {
        System.out.println("*" + chapter);
        /*
         * 调用业务
         * 获取大小时长
         * 保存文件   //文件上传 文件保存到那 文件重名  将文件保存到指定目录
         * 保存数据
         * */
        String realPath = request.getSession().getServletContext().getRealPath("/");  //webapp当前项目的路径
        File file = new File(realPath + "/upload");
        if (!file.exists()) {
            file.mkdir();
        }

        //测试音乐.MP3   11111111.MP3
        String extension = FilenameUtils.getExtension(img.getOriginalFilename());
        String s = Long.toString(new Date().getTime());

        String newName = s + "." + extension;
        System.out.println(newName + "--");
        try {
            img.transferTo(new File(file.getAbsolutePath(), newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int duration = FileUtil.getDuration(new File(file.getAbsolutePath() + "/" + newName));
        long size = img.getSize();
        double l = size / 1024.0 / 1024.0;
        System.out.println(l);


        //文件大小  时长  名字  url   date
        try {
            System.out.println("***" + chapter.getId());
            int aid = Integer.parseInt(chapter.getId());
            chapter.setId(s);
            System.out.println(aid + "-----");
            chapter.setAid(aid);
            chapter.setTitle(ctitle);
            chapter.setDuration((double) duration);
            chapter.setSize(l);
            chapter.setDownPath(newName);
            System.out.println("***" + chapter);
            chapterService.addChapter(chapter);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/download")
    public void download(String url, String title, HttpServletRequest request, HttpServletResponse response) {
        String uploadPath = request.getSession().getServletContext().getRealPath("upload");  //webapp当前项目的路径
        String path = uploadPath + "/" + url;
        File file = new File(path);

        String s = title + "." + "mp3";


        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(s, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("audio/mpeg");

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
