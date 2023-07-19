package com.example.demo.service;

import com.example.demo.dto.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public int save(int userId, Post post) {
        if (userRepository.findById(userId) == null) {
            throw new IllegalArgumentException("Can not save post, such user does not exists.");
        }

        return postRepository.save(userId, post);
    }

    public void updatePost(int userId, int postId, Post post) {
        if (userRepository.findById(userId) == null ||
            postRepository.findById(userId, postId) == null) {
            throw new IllegalArgumentException("Can not update post, user or post does not exists.");
        }

        postRepository.updateById(userId, postId, post);
    }

    public void deleteById(int userId, int postId) {
        if (userRepository.findById(userId) == null ||
                postRepository.findById(userId, postId) == null) {
            throw new IllegalArgumentException("Can not delete post, user or post does not exists.");
        }

        postRepository.delete(userId, postId);
    }

    public List<Post> getPostsByUserId(int userId) {
        if (userRepository.findById(userId) == null) {
            throw new IllegalArgumentException("Can not get post by user ID, such user does not exists.");
        }

        return postRepository.getByUserId(userId);
    }
}