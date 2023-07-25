package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.controller.dto.SortOrder;
import com.example.demo.exception.BlankStringException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;

import java.util.List;

public interface PostService {
    int save(int userId, Post post) throws UserNotFoundException, BlankStringException;
    Post update(int postId, Post post) throws PostNotFoundException;
    List<Post> getPostsByUserId(int userId, int limit, SortOrder sortOrder) throws UserNotFoundException;
    void delete(int postId) throws PostNotFoundException;
    int countByUserId(int userId) throws UserNotFoundException;
}
