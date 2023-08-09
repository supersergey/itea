package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.controller.dto.SortOrder;
import com.example.demo.exception.BlankStringException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
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
    private final PostConverter postConverter;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PostConverter postConverter) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postConverter = postConverter;
    }

    @Override
    public int save(int userId, Post post) throws UserNotFoundException, BlankStringException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (post.getTitle().isBlank() || post.getBody().isBlank()) {
            throw new BlankStringException("Fields are empty");
        }

        PostEntity postEntity = postConverter.toEntity(post);

        postEntity.setUser(user);

        return postRepository.save(postEntity).getId();
    }

    @Override
    public Post update(int postId, Post changedPost) throws PostNotFoundException {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        postEntity.setTitle(changedPost.getTitle());
        postEntity.setBody(changedPost.getBody());
        PostEntity savedPostEntity = postRepository.save(postEntity);

        return postConverter.toDto(savedPostEntity);
    }

    @Override
    public List<Post> getPostsByUserId(int userId, int limit, SortOrder sortOrder) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        List<PostEntity> postEntities = postRepository.findByUserId(userId);
        List<Post> posts = postEntities.stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());

        if (sortOrder == SortOrder.DESC) {
            Collections.reverse(posts);
        }

        if (posts.size() > limit) {
            return posts.stream()
                    .limit(limit)
                    .toList();
        }
        return posts;
    }

    @Override
    public void delete(int postId) throws PostNotFoundException {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        postRepository.delete(postEntity);
    }

    @Override
    public int countByUserId(int userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return postRepository.countByUserId(userId);
    }

}
