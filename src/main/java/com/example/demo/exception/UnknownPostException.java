package com.example.demo.exception;

public class UnknownPostException extends Exception{
    public UnknownPostException()
    {
        super("Post doesn't exist.");
    }
}
