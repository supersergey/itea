package com.example.demo.repository;

import com.example.demo.dto.Post;

import java.util.List;

public interface PostRepository {

    int save(int userId, Post post);

    List<Post> getByUserId(int userId);

    boolean delete(int userId, int postId);

    int countByUserId(int userId);

    Post findById(int userId, int postId);

    void updateById(int userId, int postId, Post post);
}
