package com.ab.cmfz;

import com.ab.cmfz.dao.MenuDao;
import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests extends TmallApplicationTests {

    @Autowired
    UserDao userDao;
    @Autowired
    MenuDao menuDao;
    @Test
    public void contextLoads() {
        User user = userDao.selectByPrimaryKey(1);
        System.out.println(user);
    }

   /* public void m1(){
        menuDao.selectByPrimaryKey();
    }*/


}
