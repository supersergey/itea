package com.example.demo.controllers;

import com.example.demo.dto.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {


    @PutMapping("/api/posts/{postId}/comments")
    public String updateComment(@PathVariable int postId,
                                @RequestBody Comment comment) {
        comment.setPostId(postId);
        System.out.println(comment);

        return comment.toString();
    }

    @PostMapping("/api/comments")
    public String addComment(@RequestBody Comment comment) {
        System.out.println(comment);
        return comment.toString();
    }

    @GetMapping("/api/posts/{postId}/comments")
    public List<Comment> getCommentsByPostId(
            @PathVariable int postId) {

        return List.of(
                new Comment((int) (Math.random() * 1000), postId, "Comment 1 to post with id " + postId),
                new Comment((int) (Math.random() * 1000), postId, "Comment 2 to post with id " + postId)
        );
    }
}
