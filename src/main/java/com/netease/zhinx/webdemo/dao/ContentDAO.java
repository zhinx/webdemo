package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.Content;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContentDAO {

    @Select("select * from content where id = #{id}")
    Content getContentById(@Param("id") int id);

    @Select("select * from content")
    List<Content> getAllContents();

    @Select("select * from content where seller_id = #{sellerId}")
    List<Content> getContentsBySellerId(@Param("sellerId") int sellerId);

    // TODO: sql语句再确认一下
    @Select("select * from content where id not in (select content_id from trx where buyer_id = #{buyerId})")
    List<Content> getUnboughtContents(@Param("buyerId") int buyerId);

    @Insert("insert into content(seller_id, price, title, summary, text, image) value(#{sellerId}, #{price}, #{title}, #{summary}, #{text}, #{image})")
    int addContent(Content content);

}
