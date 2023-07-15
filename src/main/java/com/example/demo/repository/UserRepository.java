package com.example.demo.repository;

import com.example.demo.dto.User;

import java.util.Collection;

public interface UserRepository {

    User findById(int id);

    boolean existsByUserNameAndLastName(User user);

    int save(User user);

    int count();

    Collection<User> findAll();

    void delete(int userId);

    void update(int userId, User user);
}