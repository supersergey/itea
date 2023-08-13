package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.controller.dto.SortOrder;
import com.example.demo.exception.BlankStringException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostConverter postConverter;
    @InjectMocks
    private PostServiceImpl postServiceImpl;


    @BeforeEach
    public void init() {
        Mockito.reset(userRepository, postRepository, postConverter);
    }

    @Test
    void shouldReturnPostIdWhenThereIsUserWithTheGivenIdAndPostTitleAndBodyAreNotBlank() throws UserNotFoundException, BlankStringException {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mock()));

        var convertedToEntityPostMock = mock(PostEntity.class);
        when(postConverter.toEntity(any())).thenReturn(convertedToEntityPostMock);

        var savedPostMock = mock(PostEntity.class);
        when(postRepository.save(any())).thenReturn(savedPostMock);
        when(savedPostMock.getId()).thenReturn(1);

        var postToSave = new Post("Hello", "World");
        var userId = 1;
        var actual = postServiceImpl.save(userId, postToSave);

        assertThat(actual).isEqualTo(1);

        verify(userRepository).findById(userId);
        verify(postConverter).toEntity(postToSave);
        verify(postRepository).save(convertedToEntityPostMock);
    }

    @Test
    void shouldThrowExceptionWhenUserWithDefinedIdDoesNotExist() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());


        var postToSave = new Post("Post title", "Post body");
        var userId = 1;
        var actual = catchThrowable(() -> postServiceImpl.save(userId, postToSave));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UserNotFoundException.class)
                .hasMessage(String.format("User with id=%d not found", userId));

        verify(userRepository).findById(userId);
        verify(postConverter, times(0)).toEntity(any());
        verify(postRepository, times(0)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenPostTitleAndBodyFieldsAreBlank() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mock()));

        var userId = 1;
        var postWithEmptyTitleField = new Post("", "Post body");
        var postWithEmptyBodyField = new Post("Post title", "");
        var postWhereTitleContainsOnlyWhiteSpaces = new Post("   ", "Post body");
        var postWhereBodyContainsOnlyWhiteSpaces = new Post("Post title", "   ");

        var actualWithEmptyTitleField = catchThrowable(() -> postServiceImpl.save(userId, postWithEmptyTitleField));
        var actualWithEmptyBodyField = catchThrowable(() -> postServiceImpl.save(userId, postWithEmptyBodyField));
        var actualWhereTitleContainsOnlyWhiteSpaces = catchThrowable(() -> postServiceImpl.save(userId, postWhereTitleContainsOnlyWhiteSpaces));
        var actualWhereBodyContainsOnlyWhiteSpaces = catchThrowable(() -> postServiceImpl.save(userId, postWhereBodyContainsOnlyWhiteSpaces));

        assertThat(actualWithEmptyTitleField)
                .isNotNull()
                .isExactlyInstanceOf(BlankStringException.class)
                .hasMessage("Fields are empty");

        assertThat(actualWithEmptyBodyField)
                .isNotNull()
                .isExactlyInstanceOf(BlankStringException.class)
                .hasMessage("Fields are empty");

        assertThat(actualWhereTitleContainsOnlyWhiteSpaces)
                .isNotNull()
                .isExactlyInstanceOf(BlankStringException.class)
                .hasMessage("Fields are empty");

        assertThat(actualWhereBodyContainsOnlyWhiteSpaces)
                .isNotNull()
                .isExactlyInstanceOf(BlankStringException.class)
                .hasMessage("Fields are empty");

        verify(userRepository, times(4)).findById(userId);
        verify(postConverter, times(0)).toEntity(any());
        verify(postRepository, times(0)).save(any());
    }

    @Test
    void shouldReturnUpdatedPostWhenThereIsPostWithTheGivenId() throws PostNotFoundException {
        PostEntity currentPostEntity = new PostEntity(null, "Post title", "Post body", null, null);
        Optional<PostEntity> optionalOfPostEntitySpy = Optional.of(spy(currentPostEntity));
        when(postRepository.findById(anyInt())).thenReturn(optionalOfPostEntitySpy);

        PostEntity postEntitySpy = optionalOfPostEntitySpy.get();
        PostEntity savedPostEntity = spy(new PostEntity(
                postEntitySpy.getId(),
                postEntitySpy.getTitle(),
                postEntitySpy.getBody(),
                postEntitySpy.getUser(),
                postEntitySpy.getComments()
        ));
        when(postRepository.save(any())).thenReturn(savedPostEntity);

        Answer<Post> savedPostAnswer = invocation -> {
            PostEntity spy = invocation.getArgument(0, PostEntity.class);
            return new Post(spy.getTitle(), spy.getBody());
        };
        when(postConverter.toDto(any())).thenAnswer(savedPostAnswer);

        var postId = 1;
        var postToChange = new Post("Post title", "Post body");
        var actual = postServiceImpl.update(postId, postToChange);

        assertThat(actual.getTitle()).isEqualTo("Post title");
        assertThat(actual.getBody()).isEqualTo("Post body");

        verify(postRepository).findById(postId);
        verify(postConverter).toDto(savedPostEntity);
    }

    @Test
    void shouldThrowExceptionWhenTryingToUpdatePostWithTheIdThatDoesNotExist() {
        when(postRepository.findById(anyInt())).thenReturn(Optional.empty());

        var postId = 1;
        var postToChange = new Post("Post title", "Post body");
        var actual = catchThrowable(() -> postServiceImpl.update(postId, postToChange));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(PostNotFoundException.class)
                .hasMessage(String.format("Post with id=%d not found", postId));

        verify(postRepository).findById(postId);
        verify(postRepository, times(0)).save(any());
    }

    @Test
    void shouldReturnPostsForTheGivenUserId() throws UserNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(true);

        List<PostEntity> postEntities = List.of(
                mock(),
                mock(),
                mock()
        );
        when(postRepository.findByUserId(anyInt())).thenReturn(postEntities);
        when(postConverter.toDto(any())).thenReturn(mock());

        var userId = 1;
        var limit = 2;
        var sortOrder = SortOrder.DESC;
        var actual = postServiceImpl.getPostsByUserId(userId, limit, sortOrder);

        assertThat(actual).isNotEmpty();
        assertThat(actual).hasSize(limit);

        verify(userRepository).existsById(userId);
        verify(postRepository).findByUserId(userId);
        verify(postConverter, times(3)).toDto(any(PostEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUserWithTheGivenIdDoesNotExist() throws UserNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(false);

        var userId = 1;
        var limit = 2;
        var sortOrder = SortOrder.DESC;
        var actual = catchThrowable(() -> postServiceImpl.getPostsByUserId(userId, limit, sortOrder));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UserNotFoundException.class)
                .hasMessage(String.format("User with id=%d not found", userId));

        verify(userRepository).existsById(userId);
        verify(postRepository, times(0)).findByUserId(anyInt());
        verify(postConverter, times(0)).toDto(any());
    }

    @Test
    void shouldDeletePostEntityByTheGivenId() throws PostNotFoundException {
        when(postRepository.findById(anyInt())).thenReturn(Optional.of(mock(PostEntity.class)));

        doNothing().when(postRepository).delete(any());

        var postId = 1;
        postServiceImpl.delete(postId);

        verify(postRepository).findById(postId);
        verify(postRepository).delete(any());
    }

    @Test
    void shouldThrowExceptionWhenTryingToDeletePostWithTheIdThatDoesNotExist() {
        when(postRepository.findById(anyInt())).thenReturn(Optional.empty());

        var postId = 1;
        var actual = catchThrowable(() -> postServiceImpl.delete(postId));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(PostNotFoundException.class)
                .hasMessage(String.format("Post with id=%d not found", postId));

        verify(postRepository).findById(postId);
        verify(postRepository, times(0)).delete(any());
    }

    @Test
    void shouldReturnNumberOfPostsOfTheParticularUser() throws UserNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(true);

        when(postRepository.countByUserId(anyInt())).thenReturn(2);

        var userId = 1;
        var actual = postServiceImpl.countByUserId(userId);

        assertThat(actual).isEqualTo(2);

        verify(userRepository).existsById(userId);
        verify(postRepository).countByUserId(userId);
    }

    @Test
    void shouldThrowExceptionWhenTryingToCountNumberOfUserPostsByTheUserIdThatDoesNotExist() throws UserNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(false);

        var userId = 1;
        var actual = catchThrowable(() -> postServiceImpl.countByUserId(userId));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UserNotFoundException.class)
                .hasMessage(String.format("User with id=%d not found", userId));

        verify(userRepository).existsById(userId);
        verify(postRepository, times(0)).countByUserId(anyInt());
    }
}