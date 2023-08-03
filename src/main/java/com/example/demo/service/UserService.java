package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Converter<User, com.example.demo.repository.model.User> converter;
    private final EntityManagerFactory entityManagerFactory;

    public int save(User user) throws DuplicateUserException {
        // завантаження СSV-файла
        // парсинг
        // валідація

        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var tx = entityManager.getTransaction();
            try {
                tx.begin();
                if (userRepository.existsByFirstNameAndLastName(user.name(), user.lastName())) {
                    throw new DuplicateUserException(user);
                }
                var result = userRepository.save(converter.toEntity(user)).getId();
                tx.commit();
                return result;
            } catch (Exception ex) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw ex; // Re-throw the exception to propagate it further if needed
            }
        }
    }

    public int count() {
        return (int) userRepository.count();
    }

    @Transactional(readOnly = true)
    public User findById(int id) {
        var user = userRepository.findById(id);
        return user
                .map(converter::toDto)
                .orElse(null);
    }

    public Collection<User> findAll() {
        return Collections.emptyList();
    }
}
