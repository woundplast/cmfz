package com.ab.cmfz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.ab.cmfz.entity.User;
import com.ab.cmfz.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public String login(Map map, HttpSession session, String textcode, String phoneNum, String password) {

        String kaptcha = (String) session.getAttribute("kaptcha");
        if (kaptcha.equalsIgnoreCase(textcode)) {
            User user = userService.selectUserByphoneNumAndPassword(phoneNum, password);
            if (user != null) {
                session.setAttribute("user", user);
                return "redirect:/main/main.jsp";
            } else {
                //map.put("errorNamePwd", "用户名与密码不符");
                return "login";
            }
        } else {
            map.put("errorMessage", "验证码不正确，请确认");
            return "login";
        }
    }
    @RequestMapping("/logout")
    public String over(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }


    @RequestMapping("/getCountByDays")
    public @ResponseBody
    Map getCountByDays(int[] days) {
        Map map = userService.getCountByDays(days);
        return map;

    }

    @RequestMapping("/selectUserCountAndProvinceBySex")
    public @ResponseBody
    Map selectUserCountAndProvinceBySex() {

        Map map = userService.selectUserCountAndProvinceBySex();
        System.out.println("-" + map + "-");
        return map;

    }

    @RequestMapping("/getUserAll")
    public @ResponseBody
    Map getUserAll(int page, int rows) {
        Map map = userService.getUserAll(page, rows);
        return map;

    }

    @RequestMapping("/exportUserData")
    public @ResponseBody
    void exportUserData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;fileName=User.xls");
        List<User> list = new ArrayList();
        List<User> userList = userService.queryAllUserData();
        for (User user : userList) {
            list.add(new User(user.getUsername(), user.getSex(), user.getStatus(), user.getProvince(), user.getDate()));
        }
        System.out.println(list);

        ExportParams exort = new ExportParams("用户信息", "用户数据");
        Workbook workbook = ExcelExportUtil.exportExcel(exort, User.class, list);
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("/userLogin")
    public @ResponseBody
    Object userLogin(String username, String password, String cord, HttpSession session) {
        if (username == null) {
            System.out.println("-----用户为空----");
        } else {

            User user = userService.getPasswordByUsername(username);
            if (user == null) {
                System.out.println("-----用户不存在---");
            } else {
                if (user.getPassword().equals(password)) {
                    String record = (String) session.getAttribute("record");
                    if ("1245".equals(cord)) {
                        System.out.println("------用户信息-------" + user);
                    } else {
                        System.out.println("---验证码错误----");
                    }

                } else {
                    System.out.println("-------密码错误------");
                }
            }

        }
        return null;
    }

}
