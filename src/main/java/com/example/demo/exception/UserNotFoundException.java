package com.example.demo.exception;

import com.example.demo.controller.dto.User;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(int id) {
        super(String.format("User with id=%d not found", id));
    }
}
