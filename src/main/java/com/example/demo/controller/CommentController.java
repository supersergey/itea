package com.example.demo.controller;

import com.example.demo.controller.dto.Comment;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/posts/{id}/comments")
    public int createComment(@PathVariable int id,
                          @RequestBody @Valid Comment comment) throws PostNotFoundException, UserNotFoundException {
        return commentService.save(id, comment);
    }

}
