package com.example.demo.service;

import com.example.demo.controller.dto.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements Converter<User, com.example.demo.repository.model.User> {
    @Override
    public User toDto(com.example.demo.repository.model.User model) {
        return new User(model.getFirstName(), model.getLastName());
    }

    @Override
    public com.example.demo.repository.model.User toEntity(User user) {
        return new com.example.demo.repository.model.User(null, user.getName(), user.getLastName());
    }
}