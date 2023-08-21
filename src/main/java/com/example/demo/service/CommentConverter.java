package com.example.demo.service;

import com.example.demo.controller.dto.Comment;
import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.CommentEntity;
import com.example.demo.repository.model.PostEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class CommentConverter implements Converter<Comment, CommentEntity>{
    @Override
    public Comment toDto(CommentEntity model)
    {
        return new Comment(model.getUser().getLastName(), model.getBody(), new Date(), model.getUser().getId());
    }

    @Override
    public CommentEntity toEntity(Comment comment) {
        return new CommentEntity(null, comment.getBody(), null, null);
    }
}
