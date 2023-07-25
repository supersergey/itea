package com.example.demo.service;

import com.example.demo.exception.UnknownUserException;
import com.example.demo.exception.UnknownPostException;
import com.example.demo.repository.PostRepository;
import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final Converter<Post, PostEntity> converter;
    private final UserService userService;

    public PostService(PostRepository postRepository, Converter<Post, PostEntity> converter, UserService userService) {
        this.postRepository = postRepository;
        this.converter = converter;
        this.userService = userService;
    }


    public Post save(Post post) throws UnknownUserException
    {
        if (userService.findById(post.getUserId()) == null) {
            throw new UnknownUserException();
        }
        return converter.toDto(postRepository.save(converter.toEntity(post)));
    }
   /* public void delete (int postId) throws UnknownPostException
    {
        if (postRepository.getById(postId) == null)
        {
            throw new UnknownPostException();
        }
        postRepository.delete(postId);
    }*/

    /*public int edit (int postId, Post editPost) throws UnknownPostException
    {
        Post thisPost = converter.toDto(postRepository.getById(postId));
       if (thisPost != null)
       {
           throw new UnknownPostException();
       }
       return postRepository.edit(postId, converter.toEntity(editPost));
    }*/

    public List<Post> getPostsByUserId(int userId) throws UnknownUserException
    {
        if (userService.findById(userId) == null) {
            throw new UnknownUserException();
        }
        return Collections.emptyList();
    }
}
