package com.ab.cmfz;

import com.ab.cmfz.controller.UserController;
import com.ab.cmfz.entity.User;
import com.ab.cmfz.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends TmallApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    UserController userController;

    @Test
    public void m1() {
        User user = userService.selectUserByphoneNumAndPassword("1245", "1245");
        System.out.println(user);

    }

    @Test
    public void m2() {

    }
}
