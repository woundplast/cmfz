package com.ab.cmfz.service.Impl;


import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.User;
import com.ab.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public User selectUserByphoneNumAndPassword(String phoneNum, String password) {
        return userDao.selectUserByphoneNumAndPassword(phoneNum, password);
    }

    @Override
    public Map getCountByDays(int[] days) {
        Map map = new HashMap();
        List counts = new ArrayList();
        for (int day : days) {
            int count = userDao.getCount(day);
            counts.add(count);
        }
        map.put("counts", counts);
        return map;
    }
}
