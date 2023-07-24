package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import org.springframework.stereotype.Service;

@Service
public class PostConverter implements Converter<Post, com.example.demo.repository.model.Post>{
    @Override
    public Post toDto(com.example.demo.repository.model.Post model)
    {
        return new Post(model.getTitle(), model.getBody(), model.getUserId());
    }

    @Override
    public com.example.demo.repository.model.Post toEntity(Post post) {
        return new com.example.demo.repository.model.Post(null, post.getTitle(), post.getBody(), post.getUserId());
    }
}
