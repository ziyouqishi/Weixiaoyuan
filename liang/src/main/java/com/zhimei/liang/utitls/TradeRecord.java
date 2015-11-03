package com.zhimei.liang.utitls;

import android.graphics.drawable.Drawable;

/**
 * Created by 张佳亮 on 2015/11/1.
 */
public class TradeRecord {
    private String good_name;
    private String time;
    private Drawable picture;
    private String price;
    private String ways;

    public String getWays() {
        return ways;
    }

    public void setWays(String ways) {
        this.ways = ways;
    }

    public TradeRecord(String good_name, String time, Drawable picture, String price, String ways) {
        this.good_name = good_name;
        this.time = time;
        this.picture = picture;
        this.price = price;
        this.ways = ways;

    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Drawable getPicture() {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public TradeRecord(String good_name, String time, Drawable picture, String price) {
        this.good_name = good_name;
        this.time = time;
        this.picture = picture;
        this.price = price;
    }
}
