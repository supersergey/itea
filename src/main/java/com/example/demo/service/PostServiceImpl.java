package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.controller.dto.SortOrder;
import com.example.demo.exception.BlankStringException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public int save(int userId, Post post) throws UserNotFoundException, BlankStringException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        if (post.getTitle().isBlank() || post.getBody().isBlank()) {
            throw new BlankStringException("Fields are empty");
        }

        post.setUserId(userId);
        return postRepository.save(post);
    }

    @Override
    public Post update(int postId, Post changedPost) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post does not exists");
        }
        Post post = postRepository.getById(postId);
        post.setTitle(changedPost.getTitle());
        post.setBody(changedPost.getBody());

        return post;
    }

    @Override
    public List<Post> getPostsByUserId(int userId, int limit, SortOrder sortOrder) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        List<Post> posts = postRepository.getByUserId(userId);
        List<Post> result = new ArrayList<>(posts);
        if (sortOrder == SortOrder.DESC) {
            Collections.reverse(result);
        }

        if (posts.size() > limit) {
            return result.stream()
                    .limit(limit)
                    .toList();
        }
        return result;
    }

    @Override
    public void delete(int postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post does not exists");
        }
        postRepository.delete(postId);
    }

    @Override
    public int countByUserId(int userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return postRepository.countByUserId(userId);
    }

}
