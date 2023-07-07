package com.example.demo.controller;

import com.example.demo.controller.dto.Post;
import com.example.demo.controller.dto.SortOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @GetMapping("/users/{userId}/posts")
    public List<Post> getPostsByUserId(
        @PathVariable int userId,
        @RequestParam(defaultValue = "10") int limit,
        @RequestParam(defaultValue = "DESC") SortOrder sort) {
            System.out.println(userId);
            System.out.println(limit);
            System.out.println(sort);

        return List.of(
                new Post("title1", "body1"),
                new Post("title2", "body2")
                );
    }
}
