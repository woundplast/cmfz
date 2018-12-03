package com.ab.cmfz.service;

import com.ab.cmfz.entity.Album;

import java.util.Map;

public interface AlbumService {
    /*查询全部专辑 分页*/
    Map queryAllAlbum(int page, int rows);

    /*添加专辑*/
    void addAlbum(Album album);

    /*根据专辑id查询 用于获取专辑总集数*/
    Album selectByPrimaryKey(Integer id);

    /*修改专辑集数 用于添加章节时添加对应的专辑数*/
    void updateAlumAcountById(int id, int acount);


}
