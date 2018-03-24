package com.netease.zhinx.webdemo.controller;


import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.model.DTO.ContentDTO;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.ContentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class ContentController {

    Logger logger = LogManager.getLogger(ContentController.class.getName());

    @Autowired
    ContentService contentService;

    /** 显示商品详情页 */
    @RequestMapping("/show")
    public String show(HttpServletRequest request, HttpSession session) {
        // 获取页面参数
        User user = (User) session.getAttribute("user");
        String idString = request.getParameter("id");

        if (null != idString && ! "".equals(idString)) {
            // 传入user和id参数，获取商品信息
            ContentDTO contentDTO = contentService.getContentById(user, Integer.parseInt(idString));
            if (null != contentDTO){
                request.setAttribute("product", contentDTO);
            }
        }

        return "show";
    }

    /** 转向商品发布页 */
    @RequestMapping("/public")
    public String publicNew(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (null == user) {
            return "redirect:/login";
        }

        return "public";
    }

    /** 提交发布信息 */
    @RequestMapping("/publicSubmit")
    public String publicSubmit(@RequestParam("title") String title, @RequestParam("summary") String summary, @RequestParam("image") String image,
                               @RequestParam("text") String text, @RequestParam("price") String price, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (null == user) {
            return "redirect:/login";
        }
        // 构造 Content
        Content content = new Content();
        content.setSellerId(user.getId());
        content.setPrice(Double.valueOf(price));
        content.setTitle(title);
        content.setSummary(summary);
        content.setText(text);

        // 判断图片路径是否正确，否则使用默认图片
        int imageSuffixIndex = image.lastIndexOf(".");
        Set<String> suffixes = new HashSet<String>();
        suffixes.add(".jpg");
        suffixes.add(".jpeg");
        suffixes.add(".gif");
        suffixes.add(".bmp");
        suffixes.add(".png");
        // 后缀格式不正确
        if (imageSuffixIndex == -1 || ! suffixes.contains(image.substring(imageSuffixIndex))) {
            image = "/image/default.jpg";
        }

        content.setImage(image);

        if (contentService.addContent(content)) {
            logger.debug("ContentController: new product add ok.");
            request.setAttribute("product", content);
        }

        return "publicSubmit";

    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (null == user) {
            return "redirect:/login";
        }

        int contentId = Integer.parseInt(request.getParameter("id"));

        ContentDTO contentDTO = contentService.getContentById(user, contentId);
        request.setAttribute("product", contentDTO);

        return "edit";
    }

    /** 提交修改信息 */
    @RequestMapping("/editSubmit")
    public String editSubmit(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("summary") String summary, @RequestParam("image") String image,
                             @RequestParam("text") String text, @RequestParam("price") String price, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (null == user) {
            return "redirect:/login";
        }

        Content contentToEdit = contentService.getContentById(user, id);
        if (null != contentToEdit) {

            // 删除旧图片文件
            String oldImgString = contentToEdit.getImage();
            if (! oldImgString.equals(image)) {
                if (! "/image/default.jpg".equals(oldImgString)) {
                    deleteOldImage(session, oldImgString);
                }
            }

            // 构造 Content
            Content content = new Content();
            content.setId(id);
            content.setSellerId(user.getId());
            content.setPrice(Double.valueOf(price));
            content.setTitle(title);
            content.setSummary(summary);
            content.setText(text);
            content.setImage(image);

            if (contentService.updateContent(content)) {
                logger.debug("ContentController: edit product ok.");
                request.setAttribute("id", id);
                request.setAttribute("product", content);
            }
        }
        return "editSubmit";
    }

    @RequestMapping("/api/delete")
    @ResponseBody
    public Map<String, Object> deleteContent(@RequestParam("id") int id, HttpServletResponse response, HttpSession session) {

        Map<String, Object> result = new HashMap<String, Object>();

        User user = (User) session.getAttribute("user");
        Content content = contentService.getContentById(user, id);
        if (null != user && null != content) {
            if (contentService.deleteContent(id)) {
                // 不是默认图片才删除
                if (! "/image/default.jpg".equals(content.getImage())) {
                    // 删除图片文件
                    deleteOldImage(session, content.getImage());
                }
                response.setStatus(200);
                result.put("code", 200);
                result.put("message", "删除成功");
            } else {
                response.setStatus(400);
                result.put("code", 400);
                result.put("message", "删除失败");
            }

        }
        return result;
    }

    private void deleteOldImage(HttpSession session, String oldImgString) {

        // 删除旧图片文件
        String rootPath = session.getServletContext().getRealPath("");
        File imgToDelete = new File(rootPath + oldImgString);
        if (imgToDelete.exists()) {
            if (imgToDelete.delete()) {
                logger.debug("ContentController: file " + oldImgString + " has been deleted.");
            } else {
                logger.debug("ContentController: file " + oldImgString + " delete failed.");
            }
        }

    }

}
