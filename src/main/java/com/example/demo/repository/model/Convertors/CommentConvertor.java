package com.example.demo.repository.model.Convertors;

import com.example.demo.controller.dto.Comment;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.model.CommentEntity;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConvertor implements Convertor<Comment, CommentEntity>{

    @Override
    public Comment toDto(CommentEntity e) {
        return new Comment(null, e.getBody());
    }

    @Override
    public CommentEntity toEntity(Comment comment) {
        return new CommentEntity(null, comment.getBody(), null);
    }
}
