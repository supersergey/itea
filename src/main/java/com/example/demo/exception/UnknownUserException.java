package com.example.demo.exception;

import com.example.demo.controller.dto.User;

public class UnknownUserException extends Exception{
    public UnknownUserException()
    {
        super("User doesn't exist.");
    }
}
