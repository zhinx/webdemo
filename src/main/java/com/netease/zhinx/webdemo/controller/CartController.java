package com.netease.zhinx.webdemo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.netease.zhinx.webdemo.model.DTO.CartContentDTO;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    Logger logger = LogManager.getLogger(CartController.class.getName());

    @Autowired
    CartService cartService;

    @RequestMapping("/api/addCartContent")
    @ResponseBody
    public Map<String, Object> addCartContent(@RequestBody Map<String, Object> requestJSON, HttpServletResponse response, HttpSession session){
        // 构造返回值
        Map<String, Object> result = new HashMap<String, Object>();

        // 获取 user
        User user = (User) session.getAttribute("user");

        if (null == user) {
            result.put("code", 400);
            result.put("message", "请登录");
            response.setStatus(400);
            return result;
        }

        if (null == requestJSON) {
            result.put("code", 400);
            result.put("message", "无商品信息");
            response.setStatus(400);
            return result;
        }

        //TODO: 将商品信息添加到购物车
        // 获取用户已经加入购物车的商品信息
        List<CartContentDTO> cartContentDTOs = cartService.getCartContents(user);

        boolean hasSameContent = false;
        int contentId = (Integer) requestJSON.get("id");
        String title = (String) requestJSON.get("title");
        // double price = new Double((Integer) requestJSON.get("price"));
        int num = (Integer) requestJSON.get("num");

        if (! cartContentDTOs.isEmpty()) {

            // 如果购物车中已经有同类商品，只增加数量
            for (CartContentDTO cartContentDTO :
                    cartContentDTOs) {
                if (cartContentDTO.getContentId() == contentId) {
                    hasSameContent = true;
                    cartContentDTO.setNum(cartContentDTO.getNum() + num);
                    // 更新购物车条目信息
                    cartService.updateCartContent(cartContentDTO);
                    break;
                }
            }
        }
        if (cartContentDTOs.isEmpty() || ! hasSameContent) {
            // 购物车为空 or 没有同类商品，添加条目
            CartContentDTO newCartContentDTO = new CartContentDTO();
            newCartContentDTO.setContentId(contentId);
            newCartContentDTO.setBuyerId(user.getId());
            newCartContentDTO.setNum(num);
            cartService.addCartContent(newCartContentDTO);
        }

        result.put("code", 200);
        result.put("message", "商品添加购物车成功");
        response.setStatus(200);

        return result;
    }

    /** 显示购物车页面 */
    @RequestMapping("/settleAccount")
    public String settleAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");

        if (null == user) {
            // 未登录，转向登录页面
            return "redirect:/login";
        }

        List<CartContentDTO> cartContentDTOs = cartService.getCartContents(user);

        // 构造购物车条目map
        if (null != cartContentDTOs) {
            List<Map<String, Object>> cartContentJsonObjectList = new LinkedList<Map<String, Object>>();
            for (CartContentDTO cartContentDTO :
                    cartContentDTOs) {
                Map<String, Object> cartContentJsonObject = new HashMap<String, Object>();
                // TODO: 购物车传到页面的属性中，"id" 指商品id，即 contentId
                cartContentJsonObject.put("id", cartContentDTO.getContentId());
                cartContentJsonObject.put("title", cartContentDTO.getTitle());
                cartContentJsonObject.put("price", cartContentDTO.getPrice());
                cartContentJsonObject.put("num", cartContentDTO.getNum());
                cartContentJsonObjectList.add(cartContentJsonObject);
            }

            // 转换为 json
            String jsonString = URLEncoder.encode(new ObjectMapper().writeValueAsString(cartContentJsonObjectList), "utf-8");
            logger.debug("URL encoded json：" + jsonString);

            // 将信息放入cookie
            Cookie cookie = new Cookie("products", jsonString);
            response.addCookie(cookie);
        }

        return "settleAccount";
    }

}
