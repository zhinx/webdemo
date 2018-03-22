package com.netease.zhinx.webdemo.service.impl;

import com.netease.zhinx.webdemo.dao.UserDAO;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDAO userDAO;

    public User getUser(String userName, String password) {
        // 输入为空，不查询
        if ("".equals(userName) || "".equals(password)) {
            return null;
        }

        // 根据用户名查询
        User user = userDAO.getUserByName(userName);
        if (null != user) {
            // 验证密码
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
