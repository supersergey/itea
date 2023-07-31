package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.Convertors.Convertor;
import com.example.demo.repository.model.Convertors.UserConvertor;
import com.example.demo.repository.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final Convertor<User, UserEntity> convertor;

    public UserService(UserRepository userRepository, UserConvertor convertor) {
        this.convertor = convertor;
    }

    public int save(User user) throws DuplicateUserException {
//        if (userRepository.existsByFirstNameAndLastName(user.getName(), user.getLastName())) {
//            throw new DuplicateUserException(user);
//        }
        return userRepository.save(convertor.toEntity(user)).getId();
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void delete(User user) {
//        if (userRepository.existsById(user.getId())) {
//            throw new IllegalArgumentException("Can't delete user with id " + user.getId()
//                    + ". Such user doesn't exists.");
//        }

        userRepository.delete(convertor.toEntity(user));
    }

//    public void update(int userId, User user) {
//        if (userRepository.existsById(userId)) {
//            throw new IllegalArgumentException("Can't update user with id " + userId
//                    + ". Such user doesn't exists.");
//        }
//
//        userRepository.update(userId, user.getName(), user.getLastName(), user.getAge());
//    }

    public long count() {
        return userRepository.count();
    }

    public User findById(Integer id) {
        return convertor.toDto(userRepository.findById(id).get());
    }

    public Collection<User> findAll() {
        Collection<User> users = new ArrayList<>();
        Iterable<UserEntity> found = userRepository.findAll();

        for (UserEntity user : found) {
            users.add(convertor.toDto(user));
        }

        return users;
    }
}
