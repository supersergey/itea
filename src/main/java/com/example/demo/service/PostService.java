package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.exception.UnknownUserException;
import com.example.demo.exception.UnknownPostException;
import com.example.demo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    private final UserService userService;

    public int save(Post post) throws UnknownUserException {
        if (userService.findById(post.getUserId()) == null) {
            throw new UnknownUserException();
        }
        return postRepository.save(post);
    }
    public void delete (int postId) throws UnknownPostException
    {
        Post thisPost = postRepository.getById(postId);
        if (thisPost == null)
        {
            throw new UnknownPostException();
        }
        postRepository.delete(postId);
    }

    public int edit (int postId, Post editPost) throws UnknownPostException
    {
        Post thisPost = postRepository.getById(postId);
       if (thisPost != null)
       {
           throw new UnknownPostException();
       }
       return postRepository.edit(postId, editPost);
    }

    public List<Post> getPostsByUserId(int userId) throws UnknownUserException
    {
        if (userService.findById(userId) == null) {
            throw new UnknownUserException();
        }
        return postRepository.getByUserId(userId);
    }
}
