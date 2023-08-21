package com.example.demo.service;

import com.example.demo.controller.dto.Comment;
import com.example.demo.exception.UnknownPostException;
import com.example.demo.exception.UnknownUserException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.CommentEntity;
import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private Converter<Comment, CommentEntity> converter;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    private CommentService commentService;

    @BeforeEach
    public void init() {
        Mockito.reset(commentRepository, converter, userRepository, postRepository);
        commentService = new CommentService(commentRepository, converter, userRepository, postRepository);
    }

    @Test
    void shouldReturnCommentByPostId() throws Exception {
        int postId = 123;
        Optional<PostEntity> optionalOfPostEntityMock = Optional.of(mock());
        when(postRepository.findById(anyInt())).thenReturn(optionalOfPostEntityMock);

        List<CommentEntity> comments = List.of(
                new CommentEntity(1, "First comment", null, null),
                new CommentEntity(2, "Second comment", null, null)
        );
        when(commentRepository.findByPostEntityId(postId)).thenReturn(comments);

        var actual = commentService.findByPostEntityId(postId);
        assertThat(actual).isNotEmpty();
        assertThat(actual).isEqualTo(comments);
        verify(commentRepository).findByPostEntityId(anyInt());
    }

    @Test
    void shouldNotReturnCommentByPostIdDoesNotExist() throws Exception {
        int postId = 555;
        Optional<PostEntity> optionalOfPostEntityMock = Optional.of(mock());
        when(postRepository.findById(anyInt())).thenReturn(optionalOfPostEntityMock);

        when(commentRepository.findByPostEntityId(postId)).thenReturn(Collections.emptyList());

        var actual = commentService.findByPostEntityId(postId);
        assertThat(actual).isEmpty();
        verify(commentRepository).findByPostEntityId(anyInt());
    }

    @Test
    void shouldReturnCommentByUserId() throws UnknownUserException {
        int userId = 123;
        Optional<User> optionalOfUserMock = Optional.of(mock());
        when(userRepository.findById(anyInt())).thenReturn(optionalOfUserMock);
        List<CommentEntity> comments = List.of(
                new CommentEntity(null, "First comment", null, null),
                new CommentEntity(null, "Second comment", null, null)
        );
        when(commentRepository.findByUserId(anyInt())).thenReturn(comments);

        var actual = commentService.findByUserId(userId);
        assertThat(actual).isNotEmpty();
        assertThat(actual).isEqualTo(comments);
        verify(commentRepository).findByUserId(anyInt());
    }

    @Test
    void shouldNotReturnCommentByUserIdDoesNotExist() throws UnknownUserException {
        int userId = 555;
        Optional<User> optionalOfUserMock = Optional.of(mock());
        when(userRepository.findById(anyInt())).thenReturn(optionalOfUserMock);
        when(commentRepository.findByUserId(anyInt())).thenReturn(Collections.emptyList());

        var actual = commentService.findByUserId(userId);
        assertThat(actual).isEmpty();
        verify(commentRepository).findByUserId(anyInt());
    }
}