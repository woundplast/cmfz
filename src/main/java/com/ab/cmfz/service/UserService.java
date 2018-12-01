package com.ab.cmfz.service;


import com.ab.cmfz.entity.User;

import java.util.Map;

public interface UserService {
    User selectUserByphoneNumAndPassword(String phoneNum, String password);

    Map getCountByDays(int[] days);


}
