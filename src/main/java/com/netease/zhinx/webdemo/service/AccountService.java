package com.netease.zhinx.webdemo.service;

import com.netease.zhinx.webdemo.model.DTO.TransactionDTO;
import com.netease.zhinx.webdemo.model.Transaction;
import com.netease.zhinx.webdemo.model.User;

import java.util.List;

public interface AccountService {

    List<TransactionDTO> getAllTransactions(User user);

    void addTransactions(List<Transaction> transactions);

}
