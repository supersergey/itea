package com.example.demo.repository;

import com.example.demo.repository.model.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepositoryInMemoryImpl implements PostRepository{
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private int counter = 0;

    private int nextId()
    {
        return counter++;
    }

    public List<Post> getByUserId(int userId)
    {
        return posts.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(post -> userId == post.getUserId())
                .toList();
    }

    public Post save(Post post) {
        int id = nextId();
        posts.put(id, post);
        return post;
    }

    public void delete(int postId)
    {
        posts.remove(postId);
    }

    public int countByUserId(int userId) {

        return (int)posts.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .map(Post::getUserId)
                .filter(id->userId==id).count();
    }
    public Post getById (int postId)
    {
        return posts.get(postId);
    }

    public int edit(int postId, Post updatedPost)
    {
        posts.put(postId, updatedPost);
        return postId;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
