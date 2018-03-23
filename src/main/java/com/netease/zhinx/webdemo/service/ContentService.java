package com.netease.zhinx.webdemo.service;

import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.model.DTO.ContentDTO;
import com.netease.zhinx.webdemo.model.User;

import java.util.List;

public interface ContentService {

    /** 返回所有商品 */
    List<ContentDTO> getAllContents(User user);

    /** 返回未购买商品 */
    List<ContentDTO> getAllUnboughtContents(User user);

    /** 返回给定商品信息 */
    ContentDTO getContentById(User user, int contentId);

}
