package com.example.demo.controller;

import com.example.demo.controller.dto.Post;
import com.example.demo.controller.dto.SortOrder;
import com.example.demo.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/users/{id}/posts")
    public int createPost(@PathVariable int id, @RequestBody Post post) {
        return postService.save(id, post);
    }

    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        return postService.update(id, post);
    }

    @GetMapping("/api/users/{id}/posts")
    public List<Post> getPostsByUserId(
        @PathVariable int id,
        @RequestParam(defaultValue = "10") int limit,
        @RequestParam(defaultValue = "DESC") SortOrder sort) {

        return postService.getPostsByUserId(id, limit, sort);
    }

    @DeleteMapping("/api/posts/{id}")
    public void deletePost(@PathVariable int id) {
        postService.delete(id);
    }

    @GetMapping("/api/users/{id}/posts/stats")
    public int getNumberOfPostsByUserId(@PathVariable int id) {
        return postService.countByUserId(id);
    }

}
