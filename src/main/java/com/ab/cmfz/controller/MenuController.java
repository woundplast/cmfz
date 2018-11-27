package com.ab.cmfz.controller;

import com.ab.cmfz.entity.Menu;
import com.ab.cmfz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/queryAllMenu")
    public @ResponseBody
    List queryAllMenu() {
        List<Menu> menuList = menuService.queryAll();
        System.out.println(menuList + "--------");
        return menuList;

    }

}
