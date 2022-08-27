package com.example.viewnews.bean;

import org.litepal.crud.LitePalSupport;

public class SubBean extends LitePalSupport {
    private String subName;
    private String subIntroduction;
    private int subImage;
    private String subAddress;
    private String subMine;
    private String subUserID;


    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubIntroduction() {
        return subIntroduction;
    }

    public void setSubIntroduction(String subIntroduction) {
        this.subIntroduction = subIntroduction;
    }

    public int getSubImage() {
        return subImage;
    }

    public void setSubImage(int subImage) {
        this.subImage = subImage;
    }

    public String getSubAddress() {
        return subAddress;
    }

    public void setSubAddress(String subAddress) {
        this.subAddress = subAddress;
    }

    public String getSubMine() {
        return subMine;
    }

    public void setSubMine(String subMine) {
        this.subMine = subMine;
    }

    public String getSubUserID() {
        return subUserID;
    }

    public void setSubUserID(String subUserID) {
        this.subUserID = subUserID;
    }
}
