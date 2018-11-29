package com.ab.cmfz.service;

import com.ab.cmfz.entity.Album;

import java.util.List;

public interface AlbumService {

    List<Album> queryAllAlbum();

    void addAlbum(Album album);
}
