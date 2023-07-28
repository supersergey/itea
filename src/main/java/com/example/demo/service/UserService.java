package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    private final TransactionTemplate transactionTemplate;
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final Converter<User, com.example.demo.repository.model.User> converter;

    public int save(User user) throws DuplicateUserException {
        if (userRepository.existsByFirstNameAndLastName(user.name(), user.lastName())) {
            throw new DuplicateUserException(user);
        }
        return userRepository.save(converter.toEntity(user)).getId();
    }

    public int saveAsTransaction(User user) {
        /*transactionTemplate.execute(action -> {
            userRepository.save(converter.toEntity(user));
            throw new RuntimeException("Bad error");
        });
        return -1;*/
        var tx = entityManager.getTransaction();
        try {
            tx.begin();
            userRepository.save(converter.toEntity(user));
            throw new RuntimeException("Bad error");
        } catch (Throwable ex) {
           tx.rollback();
        }
        return -1;
    }

    public int count() {
        return (int) userRepository.count();
    }

    public User findById(int id) {
        return converter.toDto(userRepository.findById(id));
    }

    public Collection<User> findAll() {
        return Collections.emptyList();
    }
}
