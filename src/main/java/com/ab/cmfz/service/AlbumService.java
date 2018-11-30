package com.ab.cmfz.service;

import com.ab.cmfz.entity.Album;

import java.util.Map;

public interface AlbumService {

    Map queryAllAlbum(int page, int rows);

    void addAlbum(Album album);
}
