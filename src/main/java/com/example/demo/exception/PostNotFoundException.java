package com.example.demo.exception;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(int id) {
        super(String.format("Post with id=%d not found", id));
    }
}
