package com.ab.cmfz.dao;

import com.ab.cmfz.entity.Album;

public interface AlbumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}