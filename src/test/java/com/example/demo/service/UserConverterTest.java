package com.example.demo.service;

import org.junit.jupiter.api.Test;
import com.example.demo.repository.model.User;
import com.example.demo.repository.model.UserRole;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {
    private final UserConverter userConverter = new UserConverter();

    @Test
    void ShouldConvertToDto() {
        var user = new User(
                null,
                "Andriy",
                "Shevchenko",
                UserRole.USER,
                null);
        var actual = userConverter.toDto(user);

        assertThat(actual.name()).isEqualTo(user.getFirstName());
        assertThat(actual.lastName()).isEqualTo(user.getLastName());
        assertThat(actual.role()).isEqualTo(user.getRole().toString());
    }

    @Test
    void ShouldConvertToEntity() {
        var user = new com.example.demo.controller.dto.User(
                "Joe",
                "Biden",
                "USER");
        var actual = userConverter.toEntity(user);

        assertThat(actual.getFirstName()).isEqualTo(user.name());
        assertThat(actual.getLastName()).isEqualTo(user.lastName());
        assertThat(actual.getRole().toString()).isEqualTo(user.role());
    }
}