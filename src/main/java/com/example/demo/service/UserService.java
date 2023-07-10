package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int save(User user) {
        if (userRepository.existsByUserNameAndLastName(user)) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(user);
    }

    public int count() {
        return userRepository.count();
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }
}
