package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.repository.model.Convertors.Convertor;
import com.example.demo.repository.model.Convertors.PostConvertor;
import com.example.demo.repository.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements Convertor<User, UserEntity> {

    @Autowired
    PostConvertor postConvertor;

    @Override
    public User toDto(UserEntity model) {
        return new User(model.getFirstName(), model.getLastName(), model.getAge(), model.getPosts()
                .stream().map(p -> postConvertor.toDto(p)).toList());
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(null, user.getName(), user.getLastName(), user.getAge(), user.getPosts()
                .stream().map(p -> postConvertor.toEntity(p)).toList());
    }
}
