package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.Content;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContentDao {

    @Select("select * from content where id = #{id}")
    Content getContentById(int id);

    @Select("select * from content")
    List<Content> getAllContents();

    @Select("select * from content where seller_id = #{sellerId}")
    List<Content> getContentBySellerId(int sellerId);

}
