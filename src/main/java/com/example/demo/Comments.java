package com.example.demo;

import java.util.Date;

public class Comments {
    private final String author;
    private String body;
    private final Date date;

    public Comments (String author, String body, Date date)
    {
        this.author = author;
        this.body = body;
        this.date = date;
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
                '}';
    }
}
