package com.example.demo.repository;

import com.example.demo.controller.dto.Comment;
import com.example.demo.repository.model.CommentEntity;
import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void saveCommentTest() {
        PostEntity postEntity = postRepository.findById(1).orElseThrow();
        CommentEntity commentEntity = new CommentEntity(null, "first comment to post 1", postEntity);

        int commentId = commentRepository.save(commentEntity).getId();
        var actual = commentRepository.findById(commentId).orElseThrow();

        assertThat(actual.getBody()).isEqualTo("first comment to post 1");
        assertThat(actual.getPost().getId()).isEqualTo(1);
    }

    @Test
    void savePostWithCommentsTest() {
        UserEntity userEntity = userRepository.findById(1).orElseThrow();
        PostEntity postEntity = new PostEntity(null, "new post title", "new post body",
                Collections.emptyList(), userEntity);
        int postId = postRepository.save(postEntity).getId();
        var actualPost = postRepository.findById(postId).orElseThrow();

        CommentEntity commentEntity = new CommentEntity(null, "first comment to new post", postEntity);
        int commentId = commentRepository.save(commentEntity).getId();
        var actualComment = commentRepository.findById(commentId).orElseThrow();

        assertThat(actualPost.getTitle()).isEqualTo("new post title");
        assertThat(actualComment.getBody()).isEqualTo("first comment to new post");
    }

    @Test
    void deleteCommentByIdTest() {
        PostEntity postEntity = postRepository.findById(1).orElseThrow();

        CommentEntity commentEntity = new CommentEntity(null, "first comment to new post", postEntity);
        int commentId = commentRepository.save(commentEntity).getId();

        commentRepository.deleteById(commentId);
        assertThat(commentRepository.findById(commentId)).isEmpty();
    }

    @Test
    void deleteCommentTest() {
        PostEntity postEntity = postRepository.findById(1).orElseThrow();

        CommentEntity commentEntity = new CommentEntity(null, "first comment to new post", postEntity);
        int commentId = commentRepository.save(commentEntity).getId();

        commentRepository.delete(commentEntity);
        assertThat(commentRepository.findById(commentId)).isEmpty();
    }

    @Test
    void countTest() {
        PostEntity postEntity1 = postRepository.findById(1).orElseThrow();
        PostEntity postEntity2 = postRepository.findById(2).orElseThrow();
        PostEntity postEntity3 = postRepository.findById(3).orElseThrow();

        List<CommentEntity> comments = new ArrayList<>(){{
                add(new CommentEntity(null, "first comment to post1", postEntity1));
                add(new CommentEntity(null, "first comment to post2", postEntity2));
                add(new CommentEntity(null, "first comment to post3", postEntity3));
        }};

        commentRepository.saveAll(comments);

        assertThat(commentRepository.count()).isEqualTo(3);
    }
}
