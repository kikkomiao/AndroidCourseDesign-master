package com.example.viewnews.bean;

public class SettingInfo {
    private String settingName;
    private int settingId;

    public SettingInfo() {
    }

    public SettingInfo(String settingName, int settingId) {
        this.settingName = settingName;
        this.settingId = settingId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    @Override
    public String toString() {
        return "SettingInfo{" +
                "settingName='" + settingName + '\'' +
                ", settingId='" + settingId + '\'' +
                '}';
    }
}
