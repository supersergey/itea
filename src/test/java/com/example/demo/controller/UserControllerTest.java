package com.example.demo.controller;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void createUser() throws Exception {
        var id = userController.createUser(
                new User("Taras", "Shevchenko", "ADMIN")
        );
        assertThat(id).isNotNull();
    }

    @Test
    void shouldFailOnSameUserNames() throws Exception {
        userController.createUser(
                new User("Taras", "Shevchenko", "ADMIN")
        );
        var actual = catchThrowable(() -> userController.createUser(
                new User("Taras", "Shevchenko", "ADMIN")
        ));
        assertThat(actual).isExactlyInstanceOf(DuplicateUserException.class);
    }
}