package com.example.demo.service;

import com.example.demo.exception.UnknownUserException;
import com.example.demo.exception.UnknownPostException;
import com.example.demo.repository.PostRepository;
import com.example.demo.controller.dto.Post;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.PostEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final Converter<Post, PostEntity> converter;
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, Converter<Post, PostEntity> converter, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.converter = converter;
        this.userRepository = userRepository;
    }


    public int save(Post post) throws UnknownUserException {
        if (!userRepository.findById(post.getUserId()).isPresent()) {
            throw new UnknownUserException(post.getUserId());
        }
        return postRepository.save(converter.toEntity(post)).getId();
    }

    public List<Post> getPostsByUserId(int userId) throws UnknownUserException {
        if (!userRepository.existsById(userId)) {
            throw new UnknownUserException(userId);
        }
        List<PostEntity> postEntities = postRepository.getByUserId(userId);
        return postEntities.stream()
                .map(converter::toDto)
                .toList();
    }

    public int countByUserId(int userId) throws UnknownUserException {
        if (!userRepository.existsById(userId)) {
            throw new UnknownUserException(userId);
        }
        return postRepository.countByUserId(userId);
    }

    public void delete(int postId) throws UnknownPostException {
        if(!postRepository.existsById(postId)){
            throw new UnknownPostException(postId);
        }
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new UnknownPostException(postId));
        postRepository.delete(postEntity);
    }
}
