package com.example.challenges.model;

import com.google.gson.annotations.SerializedName;

public class NotificationDetail {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;

    public NotificationDetail(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NotificationDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
