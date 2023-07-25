package com.example.demo.repository;

import com.example.demo.controller.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepositoryInMemoryImpl {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private int counter;
    private int nextId() {
        return counter++;
    }

    public int save(Post post) {
        int id = nextId();
        posts.put(id, post);
        return id;
    }

    public List<Post> getByUserId(int userId) {
        return Collections.emptyList();
    }

    public void delete(int postId) {
        posts.remove(postId);
    }

    public int countByUserId(int userId) {
        return getByUserId(userId).size();
    }

    public boolean existsById(int postId) {
        return posts.containsKey(postId);
    }

    public Post getById(int postId) {
        return posts.get(postId);
    }
}
