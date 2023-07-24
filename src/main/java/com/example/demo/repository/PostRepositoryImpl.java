package com.example.demo.repository;

import com.example.demo.controller.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepositoryImpl {

    private final Map<Integer, Map<Integer, Post>> posts = new HashMap<>();
    int counter;

    public int save(int userId, Post post) {
        int id = getNewId();
        if (!posts.containsKey(userId)) {
            Map<Integer, Post> postsByUserId = new HashMap<>();

            postsByUserId.put(id, post);
            posts.put(userId, postsByUserId);
        } else {
            posts.get(userId).put(id, post);
        }

        return id;
    }

    public List<Post> getByUserId(int userId) {
        return posts.get(userId).values().stream().toList();
    }

    public boolean delete(int userId, int postId) {

        Map<Integer, Post> postsByUserId = posts.get(userId);

        return postsByUserId != null && postsByUserId.remove(postId) != null;

    }

    public int countByUserId(int userId) {

        Map<Integer, Post> postsByUserId = posts.get(userId);

        return  postsByUserId != null ? postsByUserId.size() : 0;
    }

    public Post findById(int userId, int postId) {
        return posts.get(userId).get(postId);
    }

    public void updateById(int userId, int postId, Post post) {
        posts.get(userId).remove(postId);
        posts.get(userId).put(postId, post);
    }

    private int getNewId() {
        return counter++;
    }
}
