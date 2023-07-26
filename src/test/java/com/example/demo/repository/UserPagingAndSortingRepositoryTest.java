package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserPagingAndSortingRepositoryTest {
    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;

    @Test
    void shouldReturnLimitedAmountOfUsers() {
        var users = userPagingAndSortingRepository.findAll(PageRequest.of(0, 1, Sort.by("lastName").descending()));
        assertThat(users).extracting("lastName").containsExactly(
                "Smith"
//                ,
//                "Charles",
//                "Bush",
//                "Biden"
        );
    }
}