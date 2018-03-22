package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDAO {

    @Select("select * from user where user_name = #{userName}")
    User getUserByName(@Param("userName") String userName);

    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);

}
