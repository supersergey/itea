package com.example.demo.repository;

import com.example.demo.repository.model.UserEntity;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryInMemoryImpl  {

    private final Map<Integer, UserEntity> users = new ConcurrentHashMap<>();

    private int counter = 0;

    private int nextId() {
        return counter++;
    }

    public UserEntity findById(int id) {
        return users.get(id);
    }

    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        return users.entrySet().stream()
                .anyMatch(u ->
                        u.getValue().getLastName().equals(lastName) &&
                        u.getValue().getFirstName().equals(firstName)
        );
    }

    public UserEntity save(UserEntity user) {
        int id = nextId();
        users.put(id, user);
        return user;
    }

    public int count() {
        return users.size();
    }

    public Collection<UserEntity> findAll() {
        return users.values();
    }

    public void update(int userId, UserEntity user) {
        users.remove(userId);
        users.put(userId, user);
    }
}