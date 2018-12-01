package com.ab.cmfz;

import com.ab.cmfz.controller.UserController;
import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.User;
import com.ab.cmfz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest extends TmallApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;


    @Autowired
    UserController userController;

    @Test
    public void m1() {
        User user = userService.selectUserByphoneNumAndPassword("1245", "1245");
        System.out.println(user);

    }

    @Test
    public void m2() {
       /* List<UserDto> userDtos = userDao.selectUserCount(1);
        for (UserDto userDto : userDtos) {
            System.out.println("-"+userDto);
        }*/
        //Map map = userService.selectUserCountAndProvinceBySex();
        //System.out.println(map);

    }
}
