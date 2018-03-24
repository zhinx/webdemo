package com.netease.zhinx.webdemo.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {

    Logger logger = LogManager.getLogger(UploadController.class.getName());

    /** 文件上传 */
    @RequestMapping("/api/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletResponse response, HttpSession session) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();

        // 相对文件路径
        String fileURI;
        File newFile;

        if (! file.isEmpty()) {
            // 获取项目根目录
            String rootPath = session.getServletContext().getRealPath("");

            // 构造不重复随机文件名
            do {
                fileURI = "/image/" + UUID.randomUUID().toString() + file.getOriginalFilename();
                newFile = new File(rootPath + fileURI);
                logger.debug("UploadController: try random file name...");
            } while (newFile.exists());

            // 存入磁盘
            file.transferTo(newFile);
            logger.debug("UploadController: upload file ok, file path: " + fileURI);

        } else {
            // 默认图片
            fileURI = "/image/default.jpg";
            logger.debug("UploadController: upload file failed");
        }

        response.setStatus(200);
        result.put("result", fileURI);

        return result;
    }

}
