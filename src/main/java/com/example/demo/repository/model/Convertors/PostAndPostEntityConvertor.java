package com.example.demo.repository.model.Convertors;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class PostAndPostEntityConvertor implements Convertor<Post, PostEntity> {
    @Override
    public Post toDto(PostEntity p) {
        return new Post(p.getTitle(), p.getBody());
    }

    @Override
    public PostEntity toEntity(Post post) {
        return new PostEntity(null, post.getTitle(), post.getBody(), 3);
    }
}