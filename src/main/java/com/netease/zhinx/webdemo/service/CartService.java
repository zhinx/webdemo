package com.netease.zhinx.webdemo.service;

import com.netease.zhinx.webdemo.model.CartContent;
import com.netease.zhinx.webdemo.model.DTO.CartContentDTO;
import com.netease.zhinx.webdemo.model.User;

import java.util.List;

public interface CartService {

    List<CartContentDTO> getCartContents(User user);

    boolean updateCartContent(CartContent cartContent);

    boolean addCartContent(CartContent cartContent);

    boolean deleteCartContents(User user);

}
