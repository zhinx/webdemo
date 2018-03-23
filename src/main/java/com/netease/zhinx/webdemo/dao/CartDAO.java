package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.CartContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartDAO {

    @Select("select * from cart where buyer_id = #{buyerId}")
    List<CartContent> getCartContentsByBuyerId(@Param("buyerId") int buyerId);

    // TODO: 直接传对象会不会出问题？
    @Update("update cart set buyer_id = #{buyerId}, content_id = #{contentId}, num = #{num} where id = #{id}")
    int updateCartContent(CartContent cartContent);

    @Insert("insert into cart(buyer_id, content_id, num) value(#{buyerId}, #{contentId}, #{num})")
    int addCartContent(@Param("buyerId") int buyerId, @Param("contentId") int contentId, @Param("num") int num);
}
