package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.CartContent;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CartDAO {

    @Select("select * from cart where buyer_id = #{buyerId}")
    List<CartContent> getCartContentsByBuyerId(@Param("buyerId") int buyerId);

    // TODO: 直接传对象会不会出问题？
    @Update("update cart set buyer_id = #{buyerId}, content_id = #{contentId}, num = #{num} where id = #{id}")
    int updateCartContent(CartContent cartContent);

    @Insert("insert into cart(buyer_id, content_id, num) value(#{buyerId}, #{contentId}, #{num})")
    int addCartContent(@Param("buyerId") int buyerId, @Param("contentId") int contentId, @Param("num") int num);

    @Delete("delete from cart where buyer_id = #{buyerId}")
    int deleteCartContentsByBuyerId(@Param("buyerId") int buyerId);
}
