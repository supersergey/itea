package com.example.demo.repository;

import com.example.demo.repository.model.Post;
import com.example.demo.repository.model.User;
import com.example.demo.repository.model.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.repository.model.UserRole.GUEST;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Transactional
class UserRepositoryTest {

    @Autowired
    @Qualifier("springDataUserRepository")
    private UserRepository repository;

    @Test
    void shouldReturnUserById() {
        var actual = repository.findById(2);
        assertThat(actual.getFirstName()).isEqualTo("George");
        assertThat(actual.getLastName()).isEqualTo("Bush");
    }

    @Test
    void shouldReturnNullForInvalidUserId() {
        var actual = repository.findById(-1);
        assertThat(actual).isNull();
    }

    @Test
    void shouldSaveANewUser() {
        var actual = repository.save(new User(null,
                "Bobie",
                "Dylan",
                GUEST,
                emptyList()));
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);
    }

    @Test
    void shouldFindAUserByFirstAndLastName() {
        var actual = repository.existsByFirstNameAndLastName("Joe", "Biden");
        assertThat(actual).isTrue();
    }

    @Test
    void shouldNotFindAUserByWrongFirstAndLastName() {
        var actual = repository.existsByFirstNameAndLastName("ababa", "ajahj");
        assertThat(actual).isFalse();
    }

    @Test
    void shouldSaveAUserWithPosts() {
        var saved = repository.save(
                new User(null, "first", "last", GUEST, List.of(
                        new Post(null, "title", "body", null)
                ))
        );

        var actual = repository.findById(saved.getId());

        assertThat(actual.getPosts().get(0).getBody()).isEqualTo("body");
        assertThat(actual.getPosts().get(0).getTitle()).isEqualTo("title");
    }

    @Test
    void shouldSaveAUserWithPosts2() {
        var saved = repository.save(
                new User(null, "first", "last", GUEST, emptyList())
        );

        saved.setPosts(
                List.of(new Post(null, "title", "body", saved))
        );

        var actual = repository.findById(saved.getId());

        assertThat(actual.getPosts().get(0).getBody()).isEqualTo("body");
        assertThat(actual.getPosts().get(0).getTitle()).isEqualTo("title");
    }

    @Test
    void shouldSaveAUserWithPosts3() {
        var saved = repository.save(
                new User(null, "first", "last", GUEST, emptyList())
        );

        saved.setPosts(
                List.of(
                        new Post(null, "title", "body", saved),
                        new Post(null, "title1", "body1", saved)
                        )
        );

        var actual = repository.findById(saved.getId());

        assertThat(actual.getPosts().stream().map(Post::getTitle)).containsExactlyInAnyOrder("title", "title1");

        saved.setPosts(
                saved.getPosts().subList(0, 1)
        );

        actual = repository.findById(saved.getId());

        assertThat(actual.getPosts().stream().map(Post::getTitle)).containsExactlyInAnyOrder("title");
    }
}