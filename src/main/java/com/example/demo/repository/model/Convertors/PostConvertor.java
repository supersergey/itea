package com.example.demo.repository.model.Convertors;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConvertor implements Convertor<Post, PostEntity> {

    @Autowired
    CommentConvertor commentConvertor;

    @Override
    public Post toDto(PostEntity p) {
        return new Post(p.getTitle(), p.getBody(), p.getComments().stream()
                .map(c -> commentConvertor.toDto(c)).toList());
    }

    @Override
    public PostEntity toEntity(Post post) {
        return new PostEntity(null, post.getTitle(), post.getBody(), post.getComments().stream()
                .map(c -> commentConvertor.toEntity(c)).toList(), null);
    }
}