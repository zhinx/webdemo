package com.netease.zhinx.webdemo.model.DTO;

import com.netease.zhinx.webdemo.model.CartContent;

public class CartContentDTO extends CartContent {

    /** 商品标题 */
    private String title;

    /** 商品价格 */
    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
