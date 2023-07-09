package com.example.demo;

public class Post {
    private final String title;
    private final String body;

    public  Post(String title, String body)
    {
        this.body = body;
        this.title = title;
    }

    public  String getTitle()
    {
        return  title;
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
