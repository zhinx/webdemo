package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from user where user_name = #{userName}")
    User getUserByName(String userName);

    @Select("select * from user where id = #{id}")
    User getUserById(int id);

}
