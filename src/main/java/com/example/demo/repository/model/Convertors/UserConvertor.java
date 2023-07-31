package com.example.demo.repository.model.Convertors;

import com.example.demo.controller.dto.User;
import com.example.demo.repository.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor implements Convertor<User, UserEntity> {

    @Autowired
    PostConvertor postConvertor;

    @Override
    public User toDto(UserEntity e) {
        return new User(e.getFirstName(), e.getLastName(), e.getAge(),
                e.getPosts().stream().map(p -> postConvertor.toDto(p)).toList());
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(null, user.getName(), user.getLastName(), user.getAge(),
                user.getPosts().stream().map(p -> postConvertor.toEntity(p)).toList());
    }
}