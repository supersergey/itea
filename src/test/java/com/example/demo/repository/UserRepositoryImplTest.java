package com.example.demo.repository;

import org.junit.jupiter.api.Test;

class UserRepositoryImplTest {

    private final UserRepository repository = new UserRepositoryImpl();

    @Test
    void shouldReturnAUserById() {
        var user = new User("John", "Smith");

        var userId = repository.save(user);

        var actual = repository.findById(userId);
        assert user.equals(actual); // Exception виникне, якщо умова = false
    }

    @Test
    void shouldReturnNullIfUserNotFound() {
        var actual = repository.findById(-1);

        assert actual == null; // Exception виникне, якщо умова = false
    }
}