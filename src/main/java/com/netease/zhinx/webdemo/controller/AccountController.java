package com.netease.zhinx.webdemo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.model.DTO.CartContentDTO;
import com.netease.zhinx.webdemo.model.DTO.TransactionDTO;
import com.netease.zhinx.webdemo.model.Transaction;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.AccountService;
import com.netease.zhinx.webdemo.service.CartService;
import com.netease.zhinx.webdemo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    CartService cartService;

    @RequestMapping("/account")
    public String account(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null == user) {
            return "redirect:/login";
        } else {
            request.setAttribute("buyList", accountService.getAllTransactions(user));
            return "account";
        }
    }

    @RequestMapping("/api/buy")
    @ResponseBody
    public Map<String, Object> buy(@RequestBody List<Map<String, Object>> requestJSON, HttpServletResponse response, HttpSession session) {

        // 构造返回值
        Map<String, Object> result = new HashMap<String, Object>();

        User user = (User) session.getAttribute("user");
        if (null == user) {
            result.put("code", 400);
            result.put("message", "购买商品前请登录");
            response.setStatus(400);
            return result;
        }

        List<Transaction> transactions = new LinkedList<Transaction>();
        for (Map<String, Object> map :
                requestJSON) {
            Transaction transaction = new Transaction();

            int contentId = (Integer) map.get("id");
            int num = (Integer) map.get("number");
            // 数量为 0 则无效
            if (num <= 0) {
                continue;
            }

            transaction.setContentId(contentId);
            transaction.setBuyerId(user.getId());
            transaction.setNum(num);
            transaction.setBuyTime(new Date().getTime());

            // TODO: 改进json识别数据格式问题
            Object price = map.get("price");
            if (price instanceof Double) {
                transaction.setBuyPrice((Double) price);
            } else {
                transaction.setBuyPrice((Integer) price);
            }

            transactions.add(transaction);
        }
        accountService.addTransactions(transactions);

        // 清空购物车
        cartService.deleteCartContents(user);

        result.put("code", 200);
        result.put("message", "购买成功");
        response.setStatus(200);
        return result;
    }
}
