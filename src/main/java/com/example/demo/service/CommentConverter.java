package com.example.demo.service;

import com.example.demo.controller.dto.Comment;
import com.example.demo.repository.model.CommentEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter implements Converter<Comment, CommentEntity> {
    @Override
    public Comment toDto(CommentEntity e) {
        return new Comment(e.getTitle(), e.getBody(), e.getUser().getId());
    }

    @Override
    public CommentEntity toEntity(Comment comment) {
        return new CommentEntity(null, comment.getTitle(), comment.getBody(), null, null);
    }
}
