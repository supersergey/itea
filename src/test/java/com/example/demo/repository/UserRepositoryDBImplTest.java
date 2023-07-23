package com.example.demo.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class UserRepositoryDBImplTest {

    private final UserRepository userRepository = new UserRepositoryDBImpl();

    @Test
    void shouldConfirmUserExistenceByFirstNameAndLastName() {
        var actual = userRepository.existsByFirstNameAndLastName("George", "Bush");
        assertTrue(actual);
    }

    @Test
    void shouldReturnNumberOfUsers() {
        var actual = userRepository.count();
        assertEquals(4, actual);
    }

    @Test
    void shouldReturnAllUsers() {
        var actual = userRepository.findAll();
        assertThat(actual).isNotEmpty();
        assertEquals(4, actual.size());
    }
}