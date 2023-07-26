package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PostConverter implements Converter<Post, PostEntity> {
    @Override
    public Post toDto(PostEntity model) {
        return new Post(model.getTitle(), model.getBody(), model.getUser().getId());
    }

    @Override
    public PostEntity toEntity(Post post) {
        return new PostEntity(null, post.getTitle(), post.getBody(), null, Collections.emptyList());
    }
}
