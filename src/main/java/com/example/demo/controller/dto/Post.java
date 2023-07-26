package com.example.demo.controller.dto;

public class Post {
    private final String title;
    private final String body;
    private final int userId;

    public Post(String title, String body, int userId) {
        this.body = body;
        this.title = title;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
