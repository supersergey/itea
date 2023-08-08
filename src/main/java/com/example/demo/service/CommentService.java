package com.example.demo.service;

import com.example.demo.controller.dto.Comment;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.CommentEntity;
import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    public int save(int postId, Comment comment) throws PostNotFoundException, UserNotFoundException {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(
                        () -> new PostNotFoundException(postId)
                );

        int authorId = comment.getAuthorId();

        User user = userRepository
                .findById(authorId)
                .orElseThrow(
                        () -> new UserNotFoundException(authorId)
                );

        CommentEntity commentEntity = commentConverter.toEntity(comment);

        commentEntity.setPostEntity(postEntity);
        commentEntity.setUser(user);

        return commentRepository.save(commentEntity).getId();

    }
}
