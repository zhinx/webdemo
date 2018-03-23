package com.netease.zhinx.webdemo.controller;


import com.netease.zhinx.webdemo.model.DTO.ContentDTO;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ContentController {

    @Autowired
    ContentService contentService;

    /** 显示商品详情页 */
    @RequestMapping("/show")
    public String show(HttpServletRequest request, HttpSession session) {
        // 获取页面参数
        User user = (User) session.getAttribute("user");
        String idString = request.getParameter("id");

        if (null != idString && ! "".equals(idString)) {
            // 传入user和id参数，获取商品信息
            ContentDTO contentDTO = contentService.getContentById(user, Integer.parseInt(idString));
            if (null != contentDTO){
                request.setAttribute("product", contentDTO);
            }
        }

        return "show";
    }



}
