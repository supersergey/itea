package com.example.demo.exception;

import com.example.demo.controller.dto.User;

import java.util.Collections;

public class DuplicateUserException extends Exception {
    public DuplicateUserException(User user) {
        super(String.format("User already exists: %s %s", user.name(), user.lastName(), Collections.emptyList()));
    }
}
