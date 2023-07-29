package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final Converter<User, com.example.demo.repository.model.User> converter;
    private final EntityManager entityManager;

    public UserService(UserRepository userRepository,
                       PostRepository postRepository,
                       Converter<User, com.example.demo.repository.model.User> converter,
                       EntityManager entityManager) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.converter = converter;
        this.entityManager = entityManager;
    }

    public int save(User user) throws DuplicateUserException {
        // завантаження СSV-файла
        // парсинг
        // валідація
        var tx = entityManager.getTransaction();
        tx.begin();
        Integer result = null;
        try {
            if (userRepository.existsByFirstNameAndLastName(user.name(), user.lastName())) {
                throw new DuplicateUserException(user);
            }
            result = userRepository.save(converter.toEntity(user)).getId();
        } catch (Throwable ex) {
            tx.rollback();
        }
        tx.commit();
        return result == null ? -1 : result;
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

    /*
     * First way of implementation of getting the last name of users with the biggest number of posts
     * Two calls to the database are used
     * */
    public List<String> findUserWithTheBiggestNumberOfPostsUsingTwoCallsToDatabase() {
        List<Integer> userIds = postRepository.findUsersIdsWithTheBiggestNumberOfPosts();
        return userIds.stream()
                .map(id -> userRepository.findById(id).get().getLastName())
                .distinct()
                .toList();
    }

    /*
     * Second way of implementation of getting the last name of users with the biggest number of posts
     * One call to the database is used
     * */
    public List<String> findUserWithTheBiggestNumberOfPosts() {
        return userRepository.findUsersLastNamesWithTheBiggestNumberOfPosts();
    }
}
