package com.example.demo.service;

import com.example.demo.controller.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void saveAsTransaction() {
        try {
            userService.saveAsTransaction(new User("Anna", "Koval"));
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
        var actual = userService.findAll();

        assertThat(actual).extracting(User::name).doesNotContain("Anna");
    }
}