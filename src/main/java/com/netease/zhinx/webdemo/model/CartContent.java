package com.netease.zhinx.webdemo.model;

public class CartContent {

    /** 购物车条目 ID **/
    private int id;

    /** 买家 ID **/
    private int buyerId;

    /** 商品 ID **/
    private int contentId;

    /** 购买数量 **/
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
