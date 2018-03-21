package com.netease.zhinx.webdemo.model;

public class Content {

    /** 商品 ID **/
    private int id;

    /** 卖家 ID **/
    private int sellerId;

    /** 商品价格 **/
    private double price;

    /** 标题 **/
    private String title;

    /** 摘要 **/
    private String summary;

    /** 正文 **/
    private String text;

    /** 图片 **/
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
