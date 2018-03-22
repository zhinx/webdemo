package com.netease.zhinx.webdemo.service.impl;

import com.netease.zhinx.webdemo.controller.LoginController;
import com.netease.zhinx.webdemo.dao.ContentDAO;
import com.netease.zhinx.webdemo.dao.TransactionDAO;
import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.model.DTO.ContentDTO;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.ContentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    // logger
    private static Logger logger = LogManager.getLogger(LoginController.class.getName());

    @Autowired
    ContentDAO contentDAO;

    @Autowired
    TransactionDAO transactionDAO;

    /** 将 content 对象封装为数据传输对象 DTO */
    public ContentDTO getContentDTO(Content content) {
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setId(content.getId());
        contentDTO.setSellerId(content.getSellerId());
        contentDTO.setPrice(content.getPrice());
        contentDTO.setTitle(content.getTitle());
        contentDTO.setSummary(content.getSummary());
        contentDTO.setText(content.getText());
        contentDTO.setImage(content.getImage());
        return contentDTO;
    }

    /** 获取所有 content ，根据参数 user 确定封装数据 */
    public List<ContentDTO> getAllContents(User user) {

        List<ContentDTO> result = new LinkedList<ContentDTO>();

        // 查询所有商品信息
        List<Content> contentList;

        // 卖家按sellerId查询，未登录和买家按所有查询
        if (null != user && 1 == user.getUserType()) {
            contentList = contentDAO.getContentsBySellerId(user.getId());
        } else {
            contentList = contentDAO.getAllContents();
        }

        for (Content content :
                contentList) {
            ContentDTO contentDTO = getContentDTO(content);

            // 如已登录，添加额外信息
            if (null != user) {
                if (0 == user.getUserType()) {
                    // 用户是买家
                    logger.debug("ContentService: userType = 0");
                    if (! transactionDAO.getTransactionsByContentIdAndBuyerId(content.getId(), user.getId()).isEmpty()) {
                        // 对应商品存在交易记录
                        contentDTO.setIsBuy(true);
                    } else {
                        contentDTO.setIsBuy(false);
                    }
                } else {
                    // 用户是卖家
                    logger.debug("ContentService: userType = 1");
                    if (! transactionDAO.getTransactionsByContentId(content.getId()).isEmpty()) {
                        // 对应商品存在交易记录
                        contentDTO.setIsSell(true);
                    } else {
                        contentDTO.setIsSell(false);
                    }
                }
            }

            result.add(contentDTO);
        }
        return result;
    }

    public List<ContentDTO> getAllUnboughtContents() {
        return null;
    }
}
