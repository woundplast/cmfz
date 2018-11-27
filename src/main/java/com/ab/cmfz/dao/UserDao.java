package com.ab.cmfz.dao;

import com.ab.cmfz.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User selectUserByphoneNumAndPassword(@Param("phoneNum") String phoneNum, @Param("password") String password);
}