package com.ab.cmfz.service.Impl;

import com.ab.cmfz.dao.MenuDao;
import com.ab.cmfz.entity.Menu;
import com.ab.cmfz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> queryAll() {
        return menuDao.quertAll();
    }
}
