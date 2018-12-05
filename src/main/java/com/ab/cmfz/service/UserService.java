package com.ab.cmfz.service;


import com.ab.cmfz.entity.User;
import com.ab.cmfz.entity.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    User selectUserByphoneNumAndPassword(String phoneNum, String password);

    Map getCountByDays(int[] days);

    Map selectUserCountAndProvinceBySex();

    Map getUserAll(int page, int rows);

    User getPasswordByUsername(String username);

    List<User> queryAllUserData();

    /*批量添加*/
    void addMany(UserDto userDto);


}
