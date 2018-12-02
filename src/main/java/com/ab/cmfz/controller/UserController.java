package com.ab.cmfz.controller;

import com.ab.cmfz.entity.User;
import com.ab.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    boolean exportUserData() {


        return false;

    }

/*            try {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("F://xxxx.xls", "utf-8"));
    } catch (
    UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    response.setContentType("application/vnd.ms-excel");
    OutputStream outputStream = null;
    try {
        outputStream = response.getOutputStream();
    } catch (
    IOException e) {
        e.printStackTrace();
    }
    try {
        workbook.write(outputStream);
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        outputStream.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}*/

}
