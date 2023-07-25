package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import org.springframework.stereotype.Service;

@Service
public class PostConverter implements Converter<Post, PostEntity> {
    @Override
    public Post toDto(PostEntity e) {
        return new Post(e.getTitle(), e.getBody());
    }

    @Override
    public PostEntity toEntity(Post post) {
        return new PostEntity(null, post.getTitle(), post.getBody(), null);
    }
}
