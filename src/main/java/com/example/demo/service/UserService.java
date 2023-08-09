package com.example.demo.service;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final Converter<User, com.example.demo.repository.model.User> converter;

    @Transactional
    public int save(User user) throws DuplicateUserException {
        // завантаження СSV-файла
        // парсинг
        // валідація

        if (userRepository.existsByFirstNameAndLastName(user.name(), user.lastName())) {
            throw new DuplicateUserException(user);
        }
        return userRepository.save(converter.toEntity(user)).getId();
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
        return userRepository.findAll()
                .stream()
                .map(user -> new User(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole().toString())
                )
                .collect(Collectors.toList());
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
