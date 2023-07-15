package com.example.demo.repository;

import com.example.demo.controller.dto.Post;

import java.util.List;

public interface PostRepository {
    int save(Post post);
    List<Post> getByUserId(int userId);
    void delete(int postId);
    int countByUserId(int userId);
    boolean existsById(int postId);
    Post getById(int postId);

}
