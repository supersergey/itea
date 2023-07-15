package com.example.demo.repository;

import com.example.demo.dto.Post;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryImplTest {

    @Test
    void save() {
        var postRepImpl = new PostRepositoryImpl();
        List<Integer> listOdId = new ArrayList<>();
        
        listOdId.add(postRepImpl.save(1, new Post()));
        listOdId.add(postRepImpl.save(1, null));
        listOdId.add(postRepImpl.save(1, new Post()));
        listOdId.add(postRepImpl.save(2, new Post()));
        listOdId.add(postRepImpl.save(2, new Post()));

        assertArrayEquals(List.of(0, 1, 2, 3, 4).toArray(), listOdId.toArray());
    }

    @Test
    void getByUserId() {
    }

    @Test
    void delete() {
    }

    @Test
    void countByUserId() {
    }
}