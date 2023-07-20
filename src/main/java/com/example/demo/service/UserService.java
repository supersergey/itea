package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Converter<User, com.example.demo.repository.model.User> converter;

    public UserService(UserRepository userRepository, Converter<User, com.example.demo.repository.model.User> converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public int save(User user) throws DuplicateUserException {
        if (userRepository.existsByFirstNameAndLastName(user.name(), user.lastName())) {
            throw new DuplicateUserException(user);
        }
        return userRepository.save(converter.toEntity(user)).getId();
    }

    public int count() {
        return userRepository.count();
    }

    public User findById(int id) {
        return converter.toDto(userRepository.findById(id));
    }

    public Collection<User> findAll() {
        return Collections.emptyList();
    }
}
