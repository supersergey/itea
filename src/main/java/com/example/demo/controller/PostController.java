package com.example.demo.controller;

import com.example.demo.controller.dto.Post;
import com.example.demo.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/users/{userId}/posts")
    public List<Post> getPostsByUserId(@PathVariable int userId) {
        List<Post> listOfPosts = null;

        try {
            listOfPosts = postService.getPostsByUserId(userId);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        return listOfPosts;
    }

    @PostMapping("/api/users/{userId}/post")
    public Post createPost(@RequestBody Post post, @PathVariable int userId) {
        try {
            int postId = postService.save(userId, post);
            System.out.println("Post " + post.toString() + " was saved successfully with id " + postId);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        return post;
    }

    @DeleteMapping("/api/users/{userId}/posts/delete/{postId}")
    public void deletePostById(@PathVariable int userId, @PathVariable int postId) {
        try {
            postService.deleteById(postId);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @PatchMapping("api/users/{userId}/posts/update/{postId}")
    public void updatePostById(@PathVariable int userId, @PathVariable int postId, @RequestBody Post post) {
        try {
            postService.updatePost(postId, post);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
