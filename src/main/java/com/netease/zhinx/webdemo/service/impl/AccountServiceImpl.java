package com.netease.zhinx.webdemo.service.impl;

import com.netease.zhinx.webdemo.dao.ContentDAO;
import com.netease.zhinx.webdemo.dao.TransactionDAO;
import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.model.DTO.TransactionDTO;
import com.netease.zhinx.webdemo.model.Transaction;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    ContentDAO contentDAO;

    /** 将 Transaction 对象封装为数据传输对象 DTO */
    private TransactionDTO getTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId(transaction.getId());
        transactionDTO.setContentId(transaction.getContentId());
        transactionDTO.setBuyerId(transaction.getBuyerId());
        transactionDTO.setBuyPrice(transaction.getBuyPrice());
        transactionDTO.setNum(transaction.getNum());
        transactionDTO.setBuyTime(transaction.getBuyTime());

        return transactionDTO;
    }

    public List<TransactionDTO> getAllTransactions(User user) {

        List<TransactionDTO> result = new LinkedList<TransactionDTO>();

        if (null != user) {
            List<Transaction> transactions = transactionDAO.getTransactionsByBuyerId(user.getId());
            for (Transaction transaction :
                    transactions) {
                TransactionDTO transactionDTO = getTransactionDTO(transaction);
                Content content = contentDAO.getContentById(transaction.getContentId());
                transactionDTO.setImage(content.getImage());
                transactionDTO.setTitle(content.getTitle());

                result.add(transactionDTO);
            }
        }

        return result;
    }

    public void addTransactions(List<Transaction> transactions) {
        // TODO: 错误处理
        for (Transaction transaction :
                transactions) {
            transactionDAO.addTransaction(transaction);
        }
    }
}
