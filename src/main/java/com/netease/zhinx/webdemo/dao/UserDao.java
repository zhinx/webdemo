package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "userType", column = "user_type")
    })
    @Select("select * from user where user_name = #{userName}")
    User getUserByName(String userName);

}
