package com.example.demo.service;

import com.example.demo.controller.dto.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    public int save(int postId, Comment comment) {
        return 1;
    }
}
