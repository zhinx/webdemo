package com.netease.zhinx.webdemo.controller;

import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ContentService contentService;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        // TODO: 根据不同用户session展示不同首页
        List<Content> allContents = contentService.getAllContents();
        request.setAttribute("productList", allContents);
        return "index";
    }

}
