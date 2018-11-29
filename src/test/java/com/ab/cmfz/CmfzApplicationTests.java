package com.ab.cmfz;

import com.ab.cmfz.dao.AlbumDao;
import com.ab.cmfz.dao.MenuDao;
import com.ab.cmfz.dao.SlideDao;
import com.ab.cmfz.dao.UserDao;
import com.ab.cmfz.entity.Album;
import com.ab.cmfz.entity.Menu;
import com.ab.cmfz.entity.Slide;
import com.ab.cmfz.service.SlideService;
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
    AlbumDao albumDao;
    @Autowired
    MenuDao menuDao;
    @Autowired
    SlideDao slideDao;
    @Autowired
    SlideService slideService;
    @Test
    public void contextLoads() {
        int conut = slideDao.getConut();
        System.out.println(conut);
        List<Slide> userByPage = slideDao.getSlideByPage(1, 3);
        System.out.println(userByPage);


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
        slideDao.updateSlideByidAndstatus(1, 1);
    }

    @Test
    public void m3() {
        List<Album> albums = albumDao.queryAllAlbum();
        for (Album album : albums) {
            System.out.println(album);
            System.out.println("-------");
        }

    }


}
