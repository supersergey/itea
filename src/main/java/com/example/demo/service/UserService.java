package com.example.demo.service;

import com.example.demo.dto.User;
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

    public void delete(int userId) {
        if (userRepository.findById(userId) == null) {
            throw new IllegalArgumentException("Can't delete user with id " + userId
                    + ". Such user doesn't exists.");
        }

        userRepository.delete(userId);
    }

    public void update(int userId, User user) {
        if (userRepository.findById(userId) == null) {
            throw new IllegalArgumentException("Can't update user with id " + userId
                    + ". Such user doesn't exists.");
        }

        userRepository.update(userId, user);
    }

    public int count() {
        return userRepository.count();
    }

    public User findById(int id) {
        if (userRepository.findById(id) == null) {
            throw new IllegalArgumentException("Can't find user with id " + id
                    + ". Such user doesn't exists.");
        }

        return userRepository.findById(id);
    }

    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}