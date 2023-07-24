package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.Convertors.Convertor;
import com.example.demo.repository.model.Convertors.PostAndPostEntityConvertor;
import com.example.demo.repository.model.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private final Convertor<Post, PostEntity> convertor;

    @Autowired
    private UserRepository userRepository;

    public PostService(PostAndPostEntityConvertor convertor) {
        this.convertor = convertor;
    }

    public int save(int userId, Post post) {
        return postRepository.save(convertor.toEntity(post)).getId();
    }

    public void updatePost(int postId, Post post) {
//        if (userRepository.findById(userId) == null ||
//            postRepository.findById(userId, postId) == null) {
//            throw new IllegalArgumentException("Can not update post, user or post does not exists.");
//        }
//
//        postRepository.updateById(userId, postId, post);
    }

    public void delete(Post post) {
//        if (userRepository.findById(userId) == null ||
//                postRepository.findById(userId, postId) == null) {
//            throw new IllegalArgumentException("Can not delete post, user or post does not exists.");
//        }

        postRepository.delete(convertor.toEntity(post));
    }

    public void deleteById(int postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getPostsByUserId(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Can not get post by user ID, such user does not exists.");
        }

        return postRepository.getByUserId(userId).stream().map(convertor::toDto).toList();
    }

    public Post findById(int postId) {
        return convertor.toDto(postRepository.findById(postId).get());
    }

    public int count() {
        return (int) postRepository.count();
    }
}