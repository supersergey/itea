package com.example.demo.services;

import com.example.demo.controller.dto.Post;
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

    public int save(Post post) {
        if (userService.findById(post.getUserId()) == null) {
            throw new IllegalArgumentException("User doesn't exists");
        }
        return postRepository.save(post);
    }
    public void delete (int postId)
    {
        Post thisPost = postRepository.getById(postId);
        if (thisPost == null)
        {
            throw new IllegalArgumentException("Post doesn't exists");
        }
        postRepository.delete(postId);
    }

    public int edit (int postId, Post editPost)
    {
        Post thisPost = postRepository.getById(postId);
       if (thisPost != null)
       {
           throw new IllegalArgumentException("Post doesn't exists");
       }
       return postRepository.edit(postId, editPost);
    }

    public List<Post> getPostsByUserId(int userId)
    {
        return postRepository.getByUserId(userId);
    }
}
