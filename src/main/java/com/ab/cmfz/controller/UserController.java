package com.ab.cmfz.controller;

import com.ab.cmfz.entity.User;
import com.ab.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

                System.out.println("--------------");
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
}
