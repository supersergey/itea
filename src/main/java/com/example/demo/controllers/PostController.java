package com.example.demo.controllers;

import com.example.demo.dto.Comment;
import com.example.demo.dto.Post;
import com.example.demo.dto.SortOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class PostController {

    @GetMapping("/api/users/{userId}/posts")
    public List<Post> getPostsByUserId(
            @PathVariable int userId,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "DESC") SortOrder sort,
            @RequestParam(defaultValue = "0") int age) {
        System.out.println(age);
        System.out.println(userId);
        System.out.println(limit);
        System.out.println(sort);

        return List.of(
                new Post("title1", "body1"),
                new Post("title2", "body2")
        );
    }

    @GetMapping("/api/posts/{postId}/comments")
    public List<Comment> getCommentsByPostId(
            @PathVariable int postId) {

        return List.of(
                new Comment((int) (Math.random() * 1000), postId, "Comment 1 to post with id " + postId),
                new Comment((int) (Math.random() * 1000), postId, "Comment 2 to post with id " + postId)
        );

    //2. Неправильний формат ID, наприклад `curl -X GET --location "http://localhost:8080/api/posts/1a2b3c/comments"`
    //    {
    //        "timestamp": "2023-07-09T12:59:50.613+00:00",
    //            "status": 400,
    //            "error": "Bad Request",
    //            "path": "/api/posts/js124/comments"
    //    }

    }
    @PostMapping("/api/comments")
    public String addComment(@RequestBody Comment comment) {
        System.out.println(comment);
        return comment.toString();
    }

    @PutMapping("/api/posts/{postId}/comments")
    public String updateComment(@PathVariable int postId,
                                @RequestBody Comment comment) {
        comment.setPostId(postId);
        System.out.println(comment);
        return comment.toString();

//  1. Неправильний URL, наприклад `curl -X PUT --location "http://localhost:8080/api/commentssss" \`
//        {
//            "timestamp": "2023-07-09T13:02:44.487+00:00",
//                "status": 404,
//                "error": "Not Found",
//                "path": "/api/posts/124/commentssss"
//        }

    }
}
