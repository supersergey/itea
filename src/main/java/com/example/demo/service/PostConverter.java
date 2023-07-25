package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import org.springframework.stereotype.Service;

@Service
public class PostConverter implements Converter<Post, PostEntity>{
    @Override
    public Post toDto(PostEntity model)
    {
        return new Post(model.getTitle(), model.getBody(), model.getUserId());
    }

    @Override
    public PostEntity toEntity(Post post) {
        return new PostEntity(null, post.getTitle(), post.getBody(), post.getUserId());
    }
}
