package com.netease.zhinx.webdemo.service.impl;

import com.netease.zhinx.webdemo.dao.CartDAO;
import com.netease.zhinx.webdemo.dao.ContentDAO;
import com.netease.zhinx.webdemo.model.CartContent;
import com.netease.zhinx.webdemo.model.Content;
import com.netease.zhinx.webdemo.model.DTO.CartContentDTO;
import com.netease.zhinx.webdemo.model.User;
import com.netease.zhinx.webdemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartDAO cartDAO;

    @Autowired
    ContentDAO contentDAO;

    /** 将 CartContent 对象封装为数据传输对象 DTO */
    public CartContentDTO getCartContentDTO(CartContent cartContent) {
        CartContentDTO cartContentDTO = new CartContentDTO();

        cartContentDTO.setId(cartContent.getId());
        cartContentDTO.setBuyerId(cartContent.getBuyerId());
        cartContentDTO.setContentId(cartContent.getContentId());
        cartContentDTO.setNum(cartContent.getNum());

        return cartContentDTO;
    }

    /** 根据用户信息，获取购物车商品信息 */
    public List<CartContentDTO> getCartContents(User user) {

        List<CartContentDTO> result = new LinkedList<CartContentDTO>();

        if (null != user && 0 == user.getUserType()) {
            List<CartContent> cartContents = cartDAO.getCartContentsByBuyerId(user.getId());

            for (CartContent cartContent :
                    cartContents) {
                CartContentDTO cartContentDTO = getCartContentDTO(cartContent);
                // 根据商品id查询商品title
                Content content = contentDAO.getContentById(cartContent.getContentId());
                cartContentDTO.setTitle(content.getTitle());
                cartContentDTO.setPrice(content.getPrice());

                result.add(cartContentDTO);
            }
        }

        return result;
    }

    public boolean updateCartContent(CartContent cartContent) {

        return cartDAO.updateCartContent(cartContent) > 0;

    }

    public boolean addCartContent(CartContent cartContent) {

        return cartDAO.addCartContent(cartContent.getBuyerId(), cartContent.getContentId(), cartContent.getNum()) > 0;
    }
}
