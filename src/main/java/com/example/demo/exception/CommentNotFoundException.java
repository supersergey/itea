package com.example.demo.exception;

public class CommentNotFoundException extends Exception {
    public CommentNotFoundException(int id) {
        super(String.format("Comment with id=%d not found", id));
    }
}
