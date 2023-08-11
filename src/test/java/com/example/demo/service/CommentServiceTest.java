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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentConverter commentConverter;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    public void init() {
        Mockito.reset(userRepository, postRepository, commentRepository, commentConverter);
    }

    @Test
    void shouldReturnCommentIdWhenThereIsPostWithTheGivenIdAndUserWithTheGivenId() throws UserNotFoundException, PostNotFoundException {
        Optional<PostEntity> optionalOfPostEntityMock = Optional.of(mock());
        when(postRepository.findById(anyInt())).thenReturn(optionalOfPostEntityMock);

        Optional<User> optionalOfUserMock = Optional.of(mock());
        when(userRepository.findById(anyInt())).thenReturn(optionalOfUserMock);

        var commentEntityMock = mock(CommentEntity.class);
        when(commentConverter.toEntity(any())).thenReturn(commentEntityMock);

        doNothing().when(commentEntityMock).setPostEntity(any());
        doNothing().when(commentEntityMock).setUser(any());

        var savedCommentEntityMock = mock(CommentEntity.class);
        when(commentRepository.save(any())).thenReturn(savedCommentEntityMock);

        when(savedCommentEntityMock.getId()).thenReturn(1);

        var postId = 1;
        var userId = 1;
        var comment = new Comment("Comment title", "Comment body", userId);
        var actual = commentService.save(postId, comment);

        assertThat(actual).isEqualTo(1);

        verify(postRepository).findById(postId);
        verify(userRepository).findById(userId);
        verify(commentConverter).toEntity(comment);
        verify(commentRepository).save(commentEntityMock);
        verify(savedCommentEntityMock).getId();
    }

    @Test
    void shouldThrowExceptionWhenPostWithTheGivenIdDoesNotExist() {
        when(postRepository.findById(anyInt())).thenReturn(Optional.empty());

        var postId = 1;
        var userId = 1;
        var comment = new Comment("Comment title", "Comment body", userId);
        var actual = catchThrowable(() -> commentService.save(postId, comment));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(PostNotFoundException.class)
                .hasMessage(String.format("Post with id=%d not found", postId));

        verify(postRepository).findById(postId);
        verify(userRepository, times(0)).findById(anyInt());
        verify(commentConverter, times(0)).toEntity(any());
        verify(commentRepository, times(0)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUserWithTheGivenIdDoesNotExist() {
        when(postRepository.findById(anyInt())).thenReturn(Optional.of(mock()));

        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        var postId = 1;
        var userId = 1;
        var comment = new Comment("Comment title", "Comment body", userId);
        var actual = catchThrowable(() -> commentService.save(postId, comment));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UserNotFoundException.class)
                .hasMessage(String.format("User with id=%d not found", userId));

        verify(postRepository).findById(postId);
        verify(userRepository).findById(anyInt());
        verify(commentConverter, times(0)).toEntity(any());
        verify(commentRepository, times(0)).save(any());
    }
}