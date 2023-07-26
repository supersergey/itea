package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    @Qualifier("springDataUserRepository")
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void shouldReturnUserById() {
        var actual = userRepository.findById(2);
        assertThat(actual.getFirstName()).isEqualTo("George");
        assertThat(actual.getLastName()).isEqualTo("Bush");
    }

    @Test
    void shouldReturnNullForInvalidUserId() {
        var actual = userRepository.findById(-1);
        assertThat(actual).isNull();
    }

    @Test
    void shouldSaveANewUser() {
        var actual = userRepository.save(new User(null, "Bobie", "Dylan", Collections.emptyList()));
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);
    }

    @Test
    void shouldFindAUserByFirstAndLastName() {
        var actual = userRepository.existsByFirstNameAndLastName("Joe", "Biden");
        assertThat(actual).isTrue();
    }

    @Test
    void shouldNotFindAUserByWrongFirstAndLastName() {
        var actual = userRepository.existsByFirstNameAndLastName("ababa", "ajahj");
        assertThat(actual).isFalse();
    }

    @Test
    void shouldAddPostsToUser() {
        var user = new User(
                null, "Taras", "Petrenko", new ArrayList<>()
        );
        var saved = userRepository.save(user);
        postRepository.saveAll(
                Arrays.asList(
                        new PostEntity(null, "123", "456", user),
                        new PostEntity(null, "123", "456", user)
                ));

        entityManager.getEntityManagerFactory().getCache().evictAll();

        var actual = userRepository.findById(saved.getId()).get();

        assertThat(actual.getPosts()).hasSize(2);
    }

    @Test
    void shouldReturnUsersHavingAPostWithTitle() {
        var talkingUser = new User(null, "Користувач", "Говірливий", Collections.emptyList());
        var silentUser = new User(null, "Користувач", "Мовчазний", new ArrayList<>());
        userRepository.saveAll(
                Arrays.asList(talkingUser, silentUser)
        );
        talkingUser.setPosts(
                Arrays.asList(
                        new PostEntity(null, "Мій новий пост", "Не знаю, про що писати", talkingUser)
                )
        );

        var actual = userRepository.findByPostsTitle("Мій новий пост");

        assertThat(actual).extracting(User::getLastName).containsExactly("Говірливий");
    }

    @Test
    void findAllOrderByFirstNameDesc() {
        var actual = userRepository.findAllByFirstNameOrderByIdDesc("Adam");
        assertThat(actual).extracting(User::getLastName).containsExactly(
                "Smith", "Charles"
        );
    }

    @Test
    void shouldFindAUserWithMaxNumberOfPosts() {
        var actual = userRepository.findUserWithMaximalAmountOfPosts();

        assertThat(actual.getLastName()).isEqualTo("Smith");
    }
}