package com.example.demo.repository;

import com.example.demo.dto.User;
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
    public boolean existsByUserNameAndLastName(User user) {
        return users.entrySet().stream()
                .anyMatch(u ->
                        u.getValue().getName().equals(user.getName()) &&
                                u.getValue().getLastName().equals(user.getLastName())
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

    @Override
    public void delete(int userId) {
        users.remove(userId);
    }

    @Override
    public void update(int userId, User user) {
        users.remove(userId);
        users.put(userId, user);
    }
}