package com.ab.cmfz;

import com.ab.cmfz.controller.UserController;
import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.User;
import com.ab.cmfz.entity.UserDto;
import com.ab.cmfz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        User user1 = new User("阿里斯塔", 1, 0, "四川", new Date());
        List<User> users = new ArrayList<>();
        users.add(user1);
        System.out.println("---" + users);
        UserDto userDto = new UserDto();
        userDto.setUsers(users);
        userService.addMany(userDto);
        System.out.println(11);
        //Map map = userService.getUserAll(1,5);
        //System.out.println(map);

    }
}
