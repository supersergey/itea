package com.example.demo.exception;

public class UnknownPostException extends Exception{
    public UnknownPostException()
    {
        super(String.format("Post doesn't exist."));
    }
}
