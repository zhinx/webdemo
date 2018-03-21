package com.netease.zhinx.webdemo.service;

import com.netease.zhinx.webdemo.model.User;

public interface LoginService {
    // 根据用户名和密码查询用户
    User getUser(String userName, String password);

}
