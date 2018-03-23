package com.netease.zhinx.webdemo.model.DTO;

import com.netease.zhinx.webdemo.model.Transaction;

public class TransactionDTO extends Transaction {

    /** 商品图片 */
    private String image;

    /** 商品标题 */
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
