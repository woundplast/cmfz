package com.ab.cmfz.util;


import com.ab.cmfz.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * 需实现HandlerInterceptor
 * 强制登录
 *
 * @author ckt
 */
public class AuthIterceptor implements HandlerInterceptor {
    /**
     * 进入前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                             Object arg2) throws Exception {
        HttpSession session = arg0.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return true;
        } else {
            arg1.sendRedirect("login.jsp");
            return false;
        }
    }

    /**
     * 中间拦截
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    /**
     * 后拦截
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

    }


}
