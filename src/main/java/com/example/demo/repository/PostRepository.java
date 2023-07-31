package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {

    List<PostEntity> getByUserId(int userId);
}