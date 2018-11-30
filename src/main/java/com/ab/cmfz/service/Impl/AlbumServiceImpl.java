package com.ab.cmfz.service.Impl;

import com.ab.cmfz.dao.AlbumDao;
import com.ab.cmfz.entity.Album;
import com.ab.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDao albumDao;

    @Override
    public Map queryAllAlbum(int page, int rows) {
        int start = (page - 1) * rows;
        int pageSize = rows;
        Map map = new HashMap();
        int conut = albumDao.getConut();
        List<Album> albumList = albumDao.queryAllAlbum(start, pageSize);

        map.put("rows", albumList);

        map.put("total", conut);


        return map;
    }

    @Override
    public void addAlbum(Album album) {
        albumDao.addAlbum(album);
    }
}
