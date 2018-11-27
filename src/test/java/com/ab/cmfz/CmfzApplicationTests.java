package com.ab.cmfz;

import com.ab.cmfz.dao.MenuDao;
import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.Menu;
import com.ab.cmfz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests extends TmallApplicationTests {

    @Autowired
    UserDao userDao;
    @Autowired
    MenuDao menuDao;
    @Test
    public void contextLoads() {

    }

    @Test
    public void m1() {
        List<Menu> menus = menuDao.quertAll();
        for (Menu menu : menus) {
            System.out.println(menu);
        }

    }

    @Test
    public void m2() {
        User user = userDao.selectUserByphoneNumAndPassword("1245", "1245");
        System.out.println(user);
    }


}
