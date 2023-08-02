package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Post {
    @NotBlank(message = "Post title can't be blank.")
    @Size(max = 200, message = "title can't be more then 200 characters.")
    private final String title;
    @NotBlank(message = "Post body can't be blank.")
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
