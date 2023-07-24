package com.example.demo.controller;

import com.example.demo.services.PostService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.controller.dto.Post;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping ("/api/posts/new")
    public int createPost(@RequestBody Post post)
    {
        return postService.save(post);
    }

    @GetMapping("/users/{userId}/posts")
    public List<Post> getPostsUserId(@PathVariable int userId)
    {
        return postService.getPostsByUserId(userId);
    }

    @DeleteMapping("/api/posts/delete/{id}")
    public void deletePost(@PathVariable int id)
    {
        postService.delete(id);
    }

    @PutMapping ("/api/posts/edit/{id}")
    public int editPost(@RequestBody Post post, @PathVariable int id)
    {
        return postService.edit(id, post);
    }
}
