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

    public List<Post> getByUserId(int userId)
    {
        /*List<Post> postsUser = new ArrayList<Post>();
        for (Map.Entry<Integer, Post> entry: posts.entrySet())
        {
            if (entry.getValue().getUserId() == userId)
                postsUser.add(entry.getValue());
        }
        return postsUser;*/

        return posts.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(post -> userId == post.getUserId())
                .collect(Collectors.toList());
    }

    public int save(Post post) {
        int id = nextId();
        posts.put(id, post);
        return id;
    }

    public void delete(int postId)
    {
        posts.remove(postId);
    }

    public int countByUserId(int userId) {

        /*for (Map.Entry<Integer, Post> entry: posts.entrySet())
        {
            if (entry.getValue().getUserId() == userId)
                counter++;
        }
        return counter;*/
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
}
