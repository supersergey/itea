package com.example.demo.repository.model.Convertors;

import com.example.demo.controller.dto.User;
import com.example.demo.repository.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserAndUserEntityConvertor implements Convertor<User, UserEntity> {
    @Override
    public User toDto(UserEntity e) {
        return new User(e.getFirstName(), e.getLastName(), e.getAge());
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(null, user.getName(), user.getLastName(), user.getAge());
    }
}
