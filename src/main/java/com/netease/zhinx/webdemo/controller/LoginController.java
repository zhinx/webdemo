package com.netease.zhinx.webdemo.controller;

import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    // logger
    private static Logger logger = LogManager.getLogger(LoginController.class.getName());

    @Autowired
    LoginService loginService;

    /** 登录入口 **/
    @RequestMapping("/login")
    public String login(){
        // 转向 login.ftl 进行处理
        return "login";
    }

    /** 登录页面AJAX重定向 **/
    @RequestMapping("/api/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpSession session) {

        // 构造返回结果
        Map<String, Object> result = new HashMap<String, Object>();

        // 在session中找user信息
        User userFromSession = (User) session.getAttribute("user");

        // 如果没有session，处理登录
        if (null == userFromSession) {
            // 获取表单参数
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            User user = loginService.getUser(userName, password);

            if (null == user) {
                // 登录失败
                result.put("code", 400);
                if ("".equals(userName) || "".equals(password)) {
                    // 登录失败：输入空值
                    result.put("message", "用户名与密码不能为空");
                } else {
                    // 登录失败：密码错误
                    result.put("message", "密码错误");
                }
                return result;

            } else {
                // 登录正确，放入session
                session.setAttribute("user", user);
            }
        } else {
            // 如果有session，免登录，重新放入session
            session.setAttribute("user", userFromSession);
        }
        // 通过前端跳转到首页
        result.put("message", "登录成功");
        result.put("code", 200);
        return result;
    }

    /** 登出 **/
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        // 清除session的user信息
        session.removeAttribute("user");
        // 重定向到首页
        return "redirect:/";
    }

}
