package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int save(User user) throws DuplicateUserException {
        if (userRepository.existsByUserNameAndLastName(user)) {
            throw new DuplicateUserException(user);
        }
        return userRepository.save(user);
    }

    public int count() {
        return userRepository.count();
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
