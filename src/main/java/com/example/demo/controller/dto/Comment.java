package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.util.Date;

public class Comment {
    @NotBlank(message = "Author can't be blank.")
    private final String author;
    @NotBlank(message = "Comment body can't be blank.")
    private String body;// є метод set, тому не може бути поле final
    @Past(message = "Invalid date.")
    private final Date date;
    private int id;

    public Comment(String author, String body, Date date, int id) {
        this.author = author;
        this.body = body;
        this.date = date;
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setBody(@NotBlank(message = "Body can't be blank") String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
