package com.netease.zhinx.webdemo.dao;

import com.netease.zhinx.webdemo.model.Transaction;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TransactionDAO {

    // 给定商品和买家，查询交易记录，按照交易时间排序
    @Select("select * from trx where content_id = #{contentId} and buyer_id = #{buyerId} order by buy_time")
    List<Transaction> getTransactionsByContentIdAndBuyerId(@Param("contentId") int contentId, @Param("buyerId") int buyerId);

    @Select("select * from trx where content_id = #{contentId}")
    List<Transaction> getTransactionsByContentId(@Param("contentId") int contentId);

}
