package com.ab.cmfz.controller;

import com.ab.cmfz.entity.Album;
import com.ab.cmfz.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @RequestMapping("/queryAllAlbum")
    public @ResponseBody
    Map queryAllMenu(int page, int rows) {
        Map map = albumService.queryAllAlbum(page, rows);
        return map;

    }

    @RequestMapping("/addAlbum")
    public @ResponseBody
    boolean addAlbum(Album album, HttpServletRequest request, MultipartFile img) {

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


        String newName = new Date().getTime() + "." + extension;


        try {
            img.transferTo(new File(file.getAbsolutePath(), newName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            String fileName = img.getOriginalFilename();
            //获取新文件名
            album.setCoverImg(newName);

            albumService.addAlbum(album);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
