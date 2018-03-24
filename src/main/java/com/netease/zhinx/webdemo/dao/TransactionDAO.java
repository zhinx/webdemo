package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TransactionDAO {

    @Select("select * from trx where buyer_id = #{buyerId}")
    List<Transaction> getTransactionsByBuyerId(@Param("buyerId") int buyerId);

    // 给定商品和买家，查询交易记录，按照交易时间排序
    @Select("select * from trx where content_id = #{contentId} and buyer_id = #{buyerId} order by buy_time DESC")
    List<Transaction> getTransactionsByContentIdAndBuyerId(@Param("contentId") int contentId, @Param("buyerId") int buyerId);

    @Select("select * from trx where content_id = #{contentId}")
    List<Transaction> getTransactionsByContentId(@Param("contentId") int contentId);

    // TODO: 直接传对象？
    @Insert("insert into trx(content_id, buyer_id, buy_price, num, buy_time) value(#{contentId}, #{buyerId}, #{buyPrice}, #{num}, #{buyTime})")
    int addTransaction(Transaction transaction);

}
