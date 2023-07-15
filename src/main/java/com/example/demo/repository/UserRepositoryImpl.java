package com.example.demo.repository;

import com.example.demo.controller.dto.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private int counter = 0;

    private int nextId() {
        return counter++;
    }

    @Override
    public User findById(int id) {
        return users.get(id);
    }

    @Override
    public boolean existsById(int id) {
        return users.containsKey(id);
    }

    @Override
    public boolean existsByUserNameAndLastName(User user) {
        return users.entrySet().stream()
                .anyMatch(u ->
                        u.getValue().name().equals(user.name()) &&
                        u.getValue().lastName().equals(user.lastName())
        );
    }

    @Override
    public int save(User user) {
        int id = nextId();
        users.put(id, user);
        return id;
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public Collection<User> findAll() {
        return users.values();
    }
}
