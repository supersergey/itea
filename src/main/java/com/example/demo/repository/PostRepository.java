package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {
}
