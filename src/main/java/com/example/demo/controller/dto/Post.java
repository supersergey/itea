package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class Post {

    @NotBlank(message = "Post title should not be blank")
    private final String title;

    @NotBlank(message = "Post body should not be blank")
    private final String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
