package com.ab.cmfz.service.Impl;


import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.User;
import com.ab.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public User selectUserByphoneNumAndPassword(String phoneNum, String password) {
        return userDao.selectUserByphoneNumAndPassword(phoneNum, password);
    }
}
