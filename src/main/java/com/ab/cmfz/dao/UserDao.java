package com.ab.cmfz.dao;

import com.ab.cmfz.entity.User;
import com.ab.cmfz.entity.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    User selectUserByphoneNumAndPassword(@Param("phoneNum") String phoneNum, @Param("password") String password);
    /*查询人数*/
    int getCountByDate(int days);

    List<UserDto> selectUserCount(int sex);

    int getCount();

    List<User> getUserAll(@Param("start") int start, @Param("pageSize") int pageSize);

    User getPasswordByUsername(String username);

    List<User> queryAllUserData();

    /*批量导入*/

}