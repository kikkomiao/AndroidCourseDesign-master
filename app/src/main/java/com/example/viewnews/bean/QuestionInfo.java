package com.example.viewnews.bean;

public class QuestionInfo {
    private String name;
    private int layoutID;
    private int imageID;
    private int enterID;

    public QuestionInfo() {
    }

    public QuestionInfo(String name, int layoutID) {
        this.name = name;
        this.layoutID = layoutID;
    }

    public QuestionInfo(String name, int layoutID, int imageID, int enterID) {
        this.name = name;
        this.layoutID = layoutID;
        this.imageID = imageID;
        this.enterID = enterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getEnterID() {
        return enterID;
    }

    public void setEnterID(int enterID) {
        this.enterID = enterID;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "name='" + name + '\'' +
                ", layoutID=" + layoutID +
                ", imageID=" + imageID +
                ", enterID=" + enterID +
                '}';
    }
}
