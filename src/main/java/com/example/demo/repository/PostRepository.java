package com.example.demo.repository;

import com.example.demo.controller.dto.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getByUserId(int userId);
    void delete(int postId);
    int save(Post post);
    int countByUserId(int userId);
    Post getById (int postId);
    int edit(int postId, Post post);
}
