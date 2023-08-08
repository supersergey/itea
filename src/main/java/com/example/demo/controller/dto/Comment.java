package com.example.demo.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class Comment {

    @NotBlank(message = "Comment title should not be blank")
    private final String title;

    @NotBlank(message = "Comment body should not be blank")
    private final String body;

    @Min(1)
    private final int authorId;

    public Comment(String title, String body, int authorId) {
        this.title = title;
        this.body = body;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
