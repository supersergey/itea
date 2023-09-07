package com.example.demo.repository;

import com.example.demo.repository.model.User;
import com.example.demo.repository.model.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Component
public class UserDataFactory {
    private final UserRepository userRepository;

    public UserDataFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    User addNewUser(Integer id, String firstName, String lastName, UserRole role) {
        return userRepository.save(
                new User(id, firstName, lastName, role, Collections.emptyList())
        );
    }
}
