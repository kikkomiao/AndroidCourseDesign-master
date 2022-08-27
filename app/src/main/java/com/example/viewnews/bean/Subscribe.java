package com.example.viewnews.bean;

public class Subscribe {
    private String SubscribeName;
    private String SubscribeIntroduction;
    private int SubscribeImage;
    private String SubscribeAddress;

    public Subscribe() {
        super();
    }

    public Subscribe(String subscribeName, String subscribeIntroduction, int subscribeImage, String subscribeAddress) {
        SubscribeName = subscribeName;
        SubscribeIntroduction = subscribeIntroduction;
        SubscribeImage = subscribeImage;
        SubscribeAddress = subscribeAddress;
    }

    public int getSubscribeImage() {
        return SubscribeImage;
    }

    public void setSubscribeImage(int subscribeImage) {
        SubscribeImage = subscribeImage;
    }

    public String getSubscribeName() {
        return SubscribeName;
    }

    public void setSubscribeName(String subscribeName) {
        SubscribeName = subscribeName;
    }

    public String getSubscribeIntroduction() {
        return SubscribeIntroduction;
    }

    public void setSubscribeIntroduction(String subscribeIntroduction) {
        SubscribeIntroduction = subscribeIntroduction;
    }

    public String getSubscribeAddress() {
        return SubscribeAddress;
    }

    public void setSubscribeAddress(String subscribeAddress) {
        SubscribeAddress = subscribeAddress;
    }
}
