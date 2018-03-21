package com.netease.zhinx.webdemo.service.impl;

import com.netease.zhinx.webdemo.dao.ContentDao;
import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentDao contentDao;

    public List<Content> getAllContents() {
        List<Content> contentList = contentDao.getAllContents();
        return contentList;
    }
}
