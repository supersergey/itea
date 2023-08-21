package com.example.demo.service;

import com.example.demo.controller.dto.Comment;
import com.example.demo.controller.dto.Post;
import com.example.demo.exception.UnknownPostException;
import com.example.demo.exception.UnknownUserException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.CommentEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final Converter<Comment, CommentEntity> converter;
    private final Map<Integer, Comment> comments = new ConcurrentHashMap<>();
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, Converter<Comment, CommentEntity> converter, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.converter = converter;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }
    public List<CommentEntity> findByPostEntityId(int postId) throws UnknownPostException {
        if (!postRepository.findById(postId).isPresent()) {
            throw new UnknownPostException(postId);
        }
        return commentRepository.findByPostEntityId(postId);
    }
    public List<CommentEntity> findByUserId(int userId) throws UnknownUserException {
        if (!userRepository.findById(userId).isPresent()) {
            throw new UnknownUserException(userId);
        }
        return commentRepository.findByUserId(userId);
    }
}
