package com.example.demo.controller.dto;

public class Comment {

    private int id;
    private int postId;
    private String comment;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", comment='" + comment + '\'' +
                '}';
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment(int id, int postId, String comment) {
        this.postId = postId;
        this.comment = comment;
        this.id = id;
    }
}