package com.example.viewnews.bean;

import org.litepal.crud.LitePalSupport;

public class commentBean extends LitePalSupport {
    private String userId;
    private String userName;
    private String commentTime;
    private String imagePath;
    private String urlKey;
    private String commentContext;
    private String articleName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    public String getCommentContext() {
        return commentContext;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    @Override
    public String toString() {
        return "commentBean{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", urlKey='" + urlKey + '\'' +
                ", commentContext='" + commentContext + '\'' +
                ", articleName='" + articleName + '\'' +
                '}';
    }
}
