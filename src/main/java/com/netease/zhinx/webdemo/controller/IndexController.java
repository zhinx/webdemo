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
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ContentService contentService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpSession session) {
        // TODO: 根据不同用户session展示不同首页

        User user = (User) session.getAttribute("user");

        List<ContentDTO> allContents = contentService.getAllContents(user);

        request.setAttribute("productList", allContents);
        return "index";
    }

}
