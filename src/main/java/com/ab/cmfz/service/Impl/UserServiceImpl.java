package com.ab.cmfz.service.Impl;


import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.User;
import com.ab.cmfz.entity.UserDto;
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
            int count = userDao.getCountByDate(day);
            counts.add(count);
        }
        map.put("counts", counts);
        return map;
    }

    @Override
    public Map selectUserCountAndProvinceBySex() {
        Map map = new HashMap();
        List<UserDto> manList = userDao.selectUserCount(1);
        map.put("man", manList);
        List<UserDto> womenList = userDao.selectUserCount(0);
        map.put("women", womenList);

        return map;
    }

    @Override
    public Map getUserAll(int page, int rows) {
        int start = (page - 1) * rows;
        int pageSize = rows;
        Map map = new HashMap();
        int conut = userDao.getCount();
        List<User> userList = userDao.getUserAll(start, pageSize);

        map.put("rows", userList);

        map.put("total", conut);

        return map;
    }



    @Override
    public User getPasswordByUsername(String username) {
        return userDao.getPasswordByUsername(username);
    }

    @Override
    public List<User> queryAllUserData() {
        return userDao.queryAllUserData();
    }


}
