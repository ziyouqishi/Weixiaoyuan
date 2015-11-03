package com.zhimei.liang.utitls;

import android.graphics.drawable.Drawable;

/**
 * Created by 张佳亮 on 2015/11/3.
 */
public class DonationRecord {
    private String name;
    private String receiveOrganization;
    private String time;
    private Drawable picture;

    public Drawable getPicture() {
        return picture;
    }

    public DonationRecord(String name, String receiveOrganization, String time, Drawable picture) {
        this.name = name;
        this.receiveOrganization = receiveOrganization;
        this.time = time;
        this.picture = picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiveOrganization() {
        return receiveOrganization;
    }

    public void setReceiveOrganization(String receiveOrganization) {
        this.receiveOrganization = receiveOrganization;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;

    }
}
