package com.ab.cmfz.dao;

import com.ab.cmfz.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);

    List<Album> queryAllAlbum(@Param("start") int start, @Param("pageSize") int pageSize);

    int getConut();

    void addAlbum(Album album);
}