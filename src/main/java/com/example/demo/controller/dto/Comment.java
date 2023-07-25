package com.example.demo.controller.dto;

import java.util.Date;

public class Comment {
    private final String author;
    private String body;
    private final Date date;

    private  int id;

    public Comment (String author, String body, Date date, int id)
    {
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

    public  int getId() {return id;}

    public void setBody(String body)
    {
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
