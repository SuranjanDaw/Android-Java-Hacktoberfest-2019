package com.example.booksharing.model;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("userId")
    private String userId;
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
