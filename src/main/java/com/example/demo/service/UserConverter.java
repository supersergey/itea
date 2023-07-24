package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.repository.model.Convertors.Convertor;
import com.example.demo.repository.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements Convertor<User, UserEntity> {
    @Override
    public User toDto(UserEntity model) {
        return new User(model.getFirstName(), model.getLastName(), model.getAge());
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(null, user.getName(), user.getLastName(), user.getAge());
    }
}
