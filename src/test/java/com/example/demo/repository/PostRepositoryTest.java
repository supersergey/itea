package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void shouldFindPostsByUserLastName() {
        var actual = postRepository.findByUserFirstName("Smith");

        assertThat(actual).extracting(PostEntity::getTitle).containsExactly(
                "Мій перший пост", "Новий пост"
        );

    }

}