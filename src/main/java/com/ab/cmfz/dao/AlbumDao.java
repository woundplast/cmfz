package com.ab.cmfz.dao;

import com.ab.cmfz.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {

    /*查询全部专辑 分页*/
    List<Album> queryAllAlbum(@Param("start") int start, @Param("pageSize") int pageSize);
    int getConut();

    /*添加专辑*/
    void addAlbum(Album album);

    /*用于添加章节时修改对应的总集数*/
    Album selectByPrimaryKey(Integer id);
    void updateAlumAcountById(@Param("id") int id, @Param("acount") int acount);

}