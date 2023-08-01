package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Converter<User, com.example.demo.repository.model.User> converter;
    private final EntityManagerFactory entityManagerFactory;

    public UserService(UserRepository userRepository,
                       Converter<User, com.example.demo.repository.model.User> converter,
                       EntityManagerFactory entityManagerFactory) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.entityManagerFactory = entityManagerFactory;
    }

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
        return converter.toDto(userRepository.findById(id));
    }

    public Collection<User> findAll() {
        return Collections.emptyList();
    }
}
