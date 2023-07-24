package com.example.demo.repository;

import com.example.demo.repository.model.Post;
import com.example.demo.repository.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class PostRepositoryTest {
    @Autowired
    @Qualifier("springDataPostRepository")

    PostRepository repository;

    @Test
    void shouldFindByUserId()
    {
        var actual = repository.getByUserId(1);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldSaveANewPost() {
        var actual = repository.save(new Post(null, "My", "This is a new post", 2));
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);
    }

    @Test
    void shouldCountByUserId() {
        var actual = repository.countByUserId(2);
        assertEquals(1, actual);
    }

    @Test
    void shouldFindById()
    {
        var actual = repository.getById(1);
        assertThat(actual).isNotNull();
    }

    @Test
    void shouldNotFindById()
    {
        var actual = repository.getById(-1);
        assertThat(actual).isNotNull();
    }

    @Test
    void shouldFindAll()
    {
        var actual = repository.findAll();
        assertThat(actual).isNotNull();
    }
}
