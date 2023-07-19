package com.example.demo.repository;

import com.example.demo.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final Map<Integer, Map<Integer, Post>> posts = new HashMap<>();
    int counter;

    @Override
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

    @Override
    public List<Post> getByUserId(int userId) {
        return posts.get(userId).values().stream().toList();
    }

    @Override
    public boolean delete(int userId, int postId) {

        Map<Integer, Post> postsByUserId = posts.get(userId);

        return postsByUserId != null && postsByUserId.remove(postId) != null;

    }

    @Override
    public int countByUserId(int userId) {

        Map<Integer, Post> postsByUserId = posts.get(userId);

        return  postsByUserId != null ? postsByUserId.size() : 0;
    }

    @Override
    public Post findById(int userId, int postId) {
        return posts.get(userId).get(postId);
    }

    @Override
    public void updateById(int userId, int postId, Post post) {
        posts.get(userId).remove(postId);
        posts.get(userId).put(postId, post);
    }

    private int getNewId() {
        return counter++;
    }
}
