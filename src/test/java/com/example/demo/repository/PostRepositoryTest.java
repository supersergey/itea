package com.example.demo.repository;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    void shouldReturnPostById() {
        var actual = postRepository.findById(2).get();
        assertThat(actual.getTitle()).isEqualTo("Мій другий пост");
        assertThat(actual.getBody()).isEqualTo("Всім ще більше вітань!");
    }

    @Test
    void shouldSavePost() {
        var actual = postRepository.save(new PostEntity(null, "new post1", "body of new post1", 3));
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);
    }

    @Test
    void shouldCountAllPosts() {
        long actual = postRepository.count();
        assertThat(actual).isEqualTo(4);
    }

    @Test
    void shouldFindAllPosts() {
        var actual = StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        var expected = List.of(
                new PostEntity(3, "ABC", "XYZ", 2),
                new PostEntity(2, "Мій другий пост", "Всім ще більше вітань!", 4),
                new PostEntity(1, "Мій перший пост", "Всім привіт", 3),
                new PostEntity(4, "Новий пост", "Тіло нового поста", 3));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnLastNameOfUserWhichHasMostPosts() {
        var actual = userRepository.lastNameOfUserWithTheMostNumberOfPosts();
        assertThat(actual).isEqualTo("Smith");
    }
}