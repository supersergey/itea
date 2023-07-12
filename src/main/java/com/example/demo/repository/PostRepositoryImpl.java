package com.example.demo.repository;

import com.example.demo.controller.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        List<Post> postsUser = new ArrayList<Post>();
        for (Map.Entry<Integer, Post> entry: posts.entrySet())
        {
            if (entry.getValue().getUserId() == userId)
                postsUser.add(entry.getValue());
        }
        return postsUser;
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
        for (Map.Entry<Integer, Post> entry: posts.entrySet())
        {
            if (entry.getValue().getUserId() == userId)
                counter++;
        }
        return counter;
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
