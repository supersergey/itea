package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.controller.dto.SortOrder;

import java.util.List;

public interface PostService {
    int save(int userId, Post post);
    Post update(int postId, Post post);
    List<Post> getPostsByUserId(int userId, int limit, SortOrder sortOrder);
    void delete(int postId);
    int countByUserId(int userId);
}
