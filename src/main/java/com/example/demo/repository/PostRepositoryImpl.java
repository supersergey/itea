package com.example.demo.repository;

import com.example.demo.controller.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository{
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private int counter = 0;

    private int nextId()
    {
        return counter++;
    }

    public List<Post> getByUserId(int user_id)
    {

        return posts.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(post -> user_id == post.getUserId())
                .collect(Collectors.toList());
    }

    public int save(Post post) {
        int id = nextId();
        posts.put(id, post);
        return id;
    }
/*
    public void delete(int postId)
    {
        posts.remove(postId);
    }//*/

    public int countByUserId(int user_id) {

        return (int)posts.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .map(Post::getUserId)
                .filter(id->user_id==id).count();
    }
    public Post getById (int postId)
    {
        return posts.get(postId);
    }
/*
    public int edit(int postId, Post updatedPost)
    {
        posts.put(postId, updatedPost);
        return postId;
    }//*/
}
