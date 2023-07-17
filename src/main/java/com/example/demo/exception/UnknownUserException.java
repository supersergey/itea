package com.example.demo.exception;

import com.example.demo.controller.dto.User;

public class UnknownUserException extends Exception{
    public UnknownUserException()
    {
        super(String.format("User doesn't exist."));
    }
}
