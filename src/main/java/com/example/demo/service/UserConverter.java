package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.repository.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserConverter implements Converter<User, com.example.demo.repository.model.User> {
    @Override
    public User toDto(com.example.demo.repository.model.User model) {
        return new User(model.getFirstName(), model.getLastName());
    }

    @Override
    public com.example.demo.repository.model.User toEntity(User user) {
        return new com.example.demo.repository.model.User(null, user.name(), user.lastName(), UserRole.USER, Collections.emptyList());
    }
}
