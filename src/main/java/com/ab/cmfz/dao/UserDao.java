package com.ab.cmfz.dao;

import com.ab.cmfz.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    User selectUserByphoneNumAndPassword(@Param("phoneNum") String phoneNum, @Param("password") String password);
}