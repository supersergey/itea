package com.example.demo.repository;

import com.example.demo.repository.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
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
        var actual = repository.save(new User(null, "Bobie", "Dylan"));
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
}