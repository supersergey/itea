package com.example.demo.repository;

import com.example.demo.controller.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private int counter;
    private int nextId() {
        return counter++;
    }
    @Override
    public int save(Post post) {
        int id = nextId();
        posts.put(id, post);
        return id;
    }

    @Override
    public List<Post> getByUserId(int userId) {
        List<Post> postsToReturn = new ArrayList<>();
        for (Map.Entry<Integer, Post> post : posts.entrySet()) {
            if (post.getValue().getUserId() == userId) {
                postsToReturn.add(post.getValue());
            }
        }
        return postsToReturn;
    }

    @Override
    public void delete(int postId) {
        posts.remove(postId);
    }

    @Override
    public int countByUserId(int userId) {
        return getByUserId(userId).size();
    }

    @Override
    public boolean existsById(int postId) {
        return posts.containsKey(postId);
    }

    @Override
    public Post getById(int postId) {
        return posts.get(postId);
    }
}
