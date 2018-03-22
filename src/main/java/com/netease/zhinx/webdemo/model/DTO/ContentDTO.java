package com.netease.zhinx.webdemo.model.DTO;

import com.netease.zhinx.webdemo.model.Content;

public class ContentDTO extends Content{

    // 封装 Content 并添加表现层需要的信息

    /** 是否被购买过 **/
    private boolean isBuy;

    /** 是否被卖出过 **/
    private boolean isSell;

    /** 最近购买时价格 **/
    private double buyPrice;

    /** 购买数量 **/
    private int num;

    public boolean getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public boolean getIsSell() {
        return isSell;
    }

    public void setIsSell(boolean isSell) {
        this.isSell = isSell;
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
}
