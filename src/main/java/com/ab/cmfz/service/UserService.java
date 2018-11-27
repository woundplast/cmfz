package com.ab.cmfz.service;


import com.ab.cmfz.entity.User;

public interface UserService {
    User selectUserByphoneNumAndPassword(String phoneNum, String password);


}
