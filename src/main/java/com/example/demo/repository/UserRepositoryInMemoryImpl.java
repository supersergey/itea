package com.example.demo.repository;

import com.example.demo.repository.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryInMemoryImpl {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private int counter = 0;

    private int nextId() {
        return counter++;
    }

    public User findById(int id) {
        return users.get(id);
    }

    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        return users.entrySet().stream()
                .anyMatch(u ->
                        u.getValue().getLastName().equals(lastName) &&
                                u.getValue().getFirstName().equals(firstName)
                );
    }

    public User save(User user) {
        int id = nextId();
        users.put(id, user);
        return user;
    }

    public int count() {
        return users.size();
    }

    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public String getUserLastNameWithMaxPosts()
    {
        return null;
    }
}
