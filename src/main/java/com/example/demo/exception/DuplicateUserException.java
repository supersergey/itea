package com.example.demo.exception;

import com.example.demo.dto.User;

public class DuplicateUserException extends Exception {
    public DuplicateUserException(User user) {
        super(String.format("User already exists: %s %s", user.getName(), user.getLastName()));
    }
}