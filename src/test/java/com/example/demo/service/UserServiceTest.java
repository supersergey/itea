package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        when(userRepository.existsByFirstNameAndLastName(any(), any())).thenReturn(true);

        var actual = catchThrowable(() -> userService.save(new User("Taras", "Shevchenko", "ADMIN")));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(DuplicateUserException.class)
                .hasMessage("User already exists: Taras Shevchenko");

        verify(userRepository, times(0)).save(any());
    }


    @Test
    void shouldShowNumberOfUsers() {
        when(userRepository.count()).thenReturn(2L);
        var actual = userService.count();
        assertThat(actual).isEqualTo(2L);
        verify(userRepository).count();
    }

    @Test
    void shouldFindUserById() {
        Optional<com.example.demo.repository.model.User> optionalReturnUser = Optional.of(mock());
        when(userRepository.findById(anyInt())).thenReturn(optionalReturnUser);

        com.example.demo.repository.model.User userEntity = optionalReturnUser.get();

        User user = new User("Andry", "Shevchenko", "ADMIN");
        when(converter.toDto(userEntity))
                .thenReturn(user);
        var actual = userService.findById(2);
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(user);

        verify(userRepository).findById(anyInt());
    }

    @Test
    void shouldNotFindUserById() {
        Optional<com.example.demo.repository.model.User> optionalReturnUser = Optional.of(mock());
        when(userRepository.findById(anyInt())).thenReturn(optionalReturnUser);

        var actual = userService.findById(-1);
        assertThat(actual).isNull();
        verify(userRepository).findById(anyInt());
    }

    @Test
    void shouldFindAllUsers() {
        List<com.example.demo.repository.model.User> users = List.of(
                new com.example.demo.repository.model.User(null, "Set", "Green", UserRole.USER, Collections.emptyList()),
                new com.example.demo.repository.model.User(null, "John", "Smith", UserRole.ADMIN, Collections.emptyList())
        );
        when(userRepository.findAll()).thenReturn(users);

        var actual = userService.findAll();
        assertThat(actual).isNotEmpty();
        verify(userRepository).findAll();
    }
}