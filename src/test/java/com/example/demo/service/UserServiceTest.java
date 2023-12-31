package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.function.BiPredicate;

import static com.example.demo.repository.model.UserRole.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private Converter<User, com.example.demo.repository.model.User> converter;
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void init() {
        Mockito.reset(userRepository, converter);
        userService = new UserService(userRepository, converter);
    }

    @Test
    void shouldReturnAUserIdWhenThereIsNoUserWithTheGivenFirstAndLastName() throws Exception {
        when(userRepository.existsByFirstNameAndLastName(any(), any()))
                .thenReturn(false);
        var mockConvertedUser = mock(com.example.demo.repository.model.User.class);
        when(converter.toEntity(any())).thenReturn(mockConvertedUser);

        var mockModelUser = mock(com.example.demo.repository.model.User.class);
        when(mockModelUser.getId()).thenReturn(1);

        when(userRepository.save(any())).thenReturn(mockModelUser);

        var userToSave = new User("Taras", "Shevchenko", "ADMIN");

        var id = userService.save(userToSave);

        assertThat(id).isEqualTo(1);

        verify(userRepository).existsByFirstNameAndLastName("Taras", "Shevchenko");
        verify(converter).toEntity(userToSave);
        verify(userRepository).save(mockConvertedUser);
        verify(mockModelUser).getId();
    }

    @Test
    void shouldThrowExceptionWhenTheUserAlreadyExists() {
        when(userRepository.existsByFirstNameAndLastName(any(), any()))
                .thenReturn(true);

        var actual = catchThrowable(() -> userService.save(new User("Taras", "Shevchenko", "ADMIN")));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(DuplicateUserException.class)
                .hasMessage("User already exists: Taras Shevchenko");

        verify(userRepository, times(0)).save(any());
    }

    @Test
    void shouldReThrowExceptionWhenTheUserAlreadyExists() {
        when(userRepository.existsByFirstNameAndLastName(any(), any()))
                .thenThrow(new IllegalArgumentException("My custom error message"));

        var actual = catchThrowable(() -> userService.save(new User("Taras", "Shevchenko", "ADMIN")));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("My custom error message");

        verify(userRepository, times(0)).save(any());
    }

    @Test
    void shouldVerifyTheValuePassedToRepository() throws Exception {
        var user = new com.example.demo.repository.model.User(1,
                "213123",
                "13132",
                ADMIN,
                Collections.emptyList()
        );
        when(userRepository.existsByFirstNameAndLastName(any(), any())).thenReturn(false);
        when(converter.toEntity(any())).thenReturn(user);

        var captor = ArgumentCaptor.forClass(com.example.demo.repository.model.User.class);

        when(userRepository.save(captor.capture())).thenReturn(user);

        userService.save(new User("213123", "13132", "ADMIN"));

        assertThat(captor.getValue()).isEqualTo(user);
        assertThat(captor.getValue())
                .usingRecursiveComparison()
                .ignoringFields("id", "posts", "firstName", "name")
                .withEqualsForFields(
                        (BiPredicate<UserRole, String>) (userRole, s) -> userRole.name().equalsIgnoreCase(s), "role"
                )
                .isEqualTo(new User("213123", "13132", "ADMIN"));

    }
}