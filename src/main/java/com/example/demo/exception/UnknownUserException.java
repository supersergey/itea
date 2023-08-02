package com.example.demo.exception;

import com.example.demo.controller.dto.User;

public class UnknownUserException extends Exception{
    public UnknownUserException(int userId)
    {
        super(String.format("User %d doesn't exist.", userId));
    }
}
