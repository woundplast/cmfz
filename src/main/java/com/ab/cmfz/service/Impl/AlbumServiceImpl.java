package com.ab.cmfz.service.Impl;

import com.ab.cmfz.dao.AlbumDao;
import com.ab.cmfz.entity.Album;
import com.ab.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDao albumDao;

    @Override
    public List<Album> queryAllAlbum() {
        //albumDao.queryAllAlbum();
        return albumDao.queryAllAlbum();
    }
}
