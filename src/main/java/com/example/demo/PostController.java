package com.example.demo;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Post;
import com.example.demo.SortOder;

import java.util.List;

@RestController
public class PostController {
    @GetMapping("/users/{userId}/posts")
    public List<Post> getPostsUserId(@PathVariable int userId,
                                     @RequestParam(defaultValue = "10") int limit,
                                     @RequestParam(defaultValue = "DESC") SortOder sort)
    {
        System.out.println(userId);
        System.out.println(limit);
        System.out.println(sort);
        return List.of(
                new Post("title1", "body1"),
                new Post("title2", "body2")
        );
    }
}
