package com.example.demo.exception;

public class UnknownPostException extends Exception {
    public UnknownPostException(int postId) {
        super(String.format("Post %d doesn't exist.", postId));
    }
}
