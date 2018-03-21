package com.netease.zhinx.webdemo.model;

public class Transaction {

    /** 交易 ID **/
    private int id;

    /** 商品 ID **/
    private int contentId;

    /** 买家 ID **/
    private int buyerId;

    /** 购买时价格 **/
    private double buyPrice;

    /** 购买数量 **/
    private int num;

    /** 购买时间 **/
    private long buyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }
}
