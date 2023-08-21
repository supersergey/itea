package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.exception.UnknownPostException;
import com.example.demo.exception.UnknownUserException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostConverter postConverter;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostService postService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        Mockito.reset(postConverter, userRepository, postRepository);
        postService = new PostService(postRepository, postConverter, userRepository);
    }
    @Test
    void shouldReturnPostIdIsUserWithIdAndPostTitleAndBodyAreNotBlank() throws UnknownUserException {
        Optional<User> optionalOfUserMock = Optional.of(mock());
        when(userRepository.findById(anyInt())).thenReturn(optionalOfUserMock);

        var convertedToEntityPostMock = mock(PostEntity.class);
        when(postConverter.toEntity(any())).thenReturn(convertedToEntityPostMock);

        var savedPostMock = mock(PostEntity.class);
        when(postRepository.save(any())).thenReturn(savedPostMock);
        when(savedPostMock.getId()).thenReturn(1);

        var userId = 1;
        var postToSave = new Post("First", "Body", userId);
        var actual = postService.save(postToSave);

        assertThat(actual).isEqualTo(1);

        verify(userRepository).findById(userId);
        verify(postConverter).toEntity(postToSave);
        verify(postRepository).save(convertedToEntityPostMock);
    }

    @Test
    void shouldThrowExceptionWhenUserWithDefinedIdDoesNotExist() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        var userId = 1;
        var postToSave = new Post("Some", "Body", userId);
        var actual = catchThrowable(() -> postService.save(postToSave));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UnknownUserException.class)
                .hasMessage(String.format("User %d doesn't exist.", userId));

        verify(userRepository).findById(userId);
        verify(postConverter, times(0)).toEntity(any());
        verify(postRepository, times(0)).save(any());
    }

    @Test
    void shouldReturnPostsForTheGivenUserId() throws UnknownUserException {
        when(userRepository.existsById(anyInt())).thenReturn(true);
        List<PostEntity> postEntities = List.of(
                mock(),
                mock()
        );
        when(postRepository.getByUserId(anyInt())).thenReturn(postEntities);
        when(postConverter.toDto(any())).thenReturn(mock());
        var userId = 1;
        var actual = postService.getPostsByUserId(userId);

        assertThat(actual.size()).isNotZero();
        verify(userRepository).existsById(userId);
        verify(postRepository).getByUserId(userId);
        verify(postConverter, times(2)).toDto(any(PostEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUserWithTheGivenIdDoesNotExist() throws UnknownUserException {
        when(userRepository.existsById(anyInt())).thenReturn(false);

        var userId = 1;
        var actual = catchThrowable(() -> postService.getPostsByUserId(userId));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UnknownUserException.class)
                .hasMessage(String.format("User %d doesn't exist.", userId));

        verify(userRepository).existsById(userId);
    }

    @Test
    void shouldReturnNumberOfPostsOfTheParticularUser() throws UnknownUserException {
        when(userRepository.existsById(anyInt())).thenReturn(true);

        when(postRepository.countByUserId(anyInt())).thenReturn(5);

        var userId = 1;
        var actual = postService.countByUserId(userId);

        assertThat(actual).isEqualTo(5);

        verify(userRepository).existsById(userId);
        verify(postRepository).countByUserId(userId);
    }

    @Test
    void shouldThrowExceptionWhenTryingToCountNumberOfUserPostsByTheUserIdThatDoesNotExist() throws UnknownUserException {
        when(userRepository.existsById(anyInt())).thenReturn(false);

        var userId = 1;
        var actual = catchThrowable(() -> postService.countByUserId(userId));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UnknownUserException.class)
                .hasMessage(String.format("User %d doesn't exist.", userId));

        verify(userRepository).existsById(userId);
        verify(postRepository, times(0)).countByUserId(anyInt());
    }

    @Test
    void shouldDeletePostEntityByTheGivenId() throws UnknownPostException {
        when(postRepository.existsById(anyInt())).thenReturn(true);
        when(postRepository.findById(anyInt())).thenReturn(Optional.of(mock(PostEntity.class)));
        doNothing().when(postRepository).delete(any());

        var postId = 1;
        postService.delete(postId);

        verify(postRepository).existsById(postId);
        verify(postRepository).delete(any());
    }

    @Test
    void shouldThrowExceptionWhenTryingToDeletePostWithTheIdThatDoesNotExist() {
        when(postRepository.existsById(anyInt())).thenReturn(false);

        var postId = 1;
        var actual = catchThrowable(() -> postService.delete(postId));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UnknownPostException.class)
                .hasMessage(String.format("Post %d doesn't exist.", postId));

        verify(postRepository).existsById(postId);
        verify(postRepository, times(0)).delete(any());
    }
}