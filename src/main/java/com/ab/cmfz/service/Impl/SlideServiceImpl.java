package com.ab.cmfz.service.Impl;

import com.ab.cmfz.dao.SlideDao;
import com.ab.cmfz.entity.Slide;
import com.ab.cmfz.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("slideService")
@Transactional
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideDao slideDao;

    @Override
    public Map getSileByPage(int page, int rows) {
        int start = (page - 1) * rows;
        int end = page * rows;
        Map map = new HashMap();
        int conut = slideDao.getConut();
        List<Slide> slideByPage = slideDao.getSlideByPage(start, end);
        System.out.println(start);
        System.out.println(end);
        System.out.println(slideDao);
        System.out.println("-+-" + slideByPage);
        map.put("rows", slideByPage);

        map.put("total", conut);

        return map;
    }
}
