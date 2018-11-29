package com.ab.cmfz.controller;

import com.ab.cmfz.entity.Album;
import com.ab.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @RequestMapping("/queryAllAlbum")
    public @ResponseBody
    List queryAllMenu() {
        List<Album> albumList = albumService.queryAllAlbum();
        System.out.println(albumList + "--------");
        return albumList;

    }

}
