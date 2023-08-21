package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class PostRepositoryTest {
    @Autowired
    @Qualifier("springDataPostRepository")

    PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Test

    void shouldFindByUserId() {
        var actual = repository.getByUserId(2);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0).getTitle()).isEqualTo("ABC");
        assertThat(actual.get(0).getBody()).isEqualTo("XYZ");
    }

    @Test
    void shouldSaveANewPost() {
        var user = userRepository.findById(2);
        var actual = repository.save(new PostEntity(null, "My", "This is a new post", user, Collections.emptyList()));
        assertThat(actual).isNotNull();
        assertThat(actual.getId());
    }

    @Test
    void shouldCountByUserId() {
        var actual = repository.countByUserId(2);
        assertEquals(1, actual);
    }

    @Test
    void shouldFindById() {
        var actual = repository.findById(3);
        assertThat(actual).isNotNull();
        assertThat(actual.get().getTitle()).isEqualTo("ABC");
        assertThat(actual.get().getBody()).isEqualTo("XYZ");
    }

    @Test
    void shouldNotFindById() {
        var actual = repository.findById(-1);
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldFindAll() {
        var actual = repository.findAll();
        assertThat(actual).isNotNull();
    }
}
    @Transactional
    void findUserByPostTitle() {
        var userWithPostTitle = userRepository.save(
                new User(
                        null, "Taras", "Petrenko", UserRole.USER, new ArrayList<>())
        );
        userWithPostTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Post Title", "PostBody", userWithPostTitle))
        );

        var userWithWithoutTitle = userRepository.save(
                new User(
                        null, "Petro", "Petrenko", UserRole.USER, new ArrayList<>())
        );
        userWithWithoutTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Another title", "PostBody", userWithWithoutTitle))
        );

        var actual = postRepository.findUserByPostTitle("Post Title");

        assertThat(actual).extracting(User::getLastName)
                .containsExactly("Petrenko");
    }
}
