package com.netease.zhinx.webdemo.controller;

import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.model.DTO.ContentDTO;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ContentService contentService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpSession session) {
        // TODO: 根据不同用户session展示不同首页

        // 获取session用户信息
        User user = (User) session.getAttribute("user");

        // 获取页面请求的商品列表类型
        String listType = request.getParameter("type");

        List<ContentDTO> contents;

        // TODO: 参数判空
        if (null != user && 0 == user.getUserType() && listType != null && 1 == Integer.parseInt(listType)) {
            // 买家请求未购买页面
            contents = contentService.getAllUnboughtContents(user);
        } else {
            // 买家请求全部商品页面 / 卖家商品页面 / 未登录商品页面
            contents = contentService.getAllContents(user);
        }

        request.setAttribute("productList", contents);
        return "index";
    }

}
