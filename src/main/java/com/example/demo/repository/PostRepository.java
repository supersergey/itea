package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {

    @Query("""
            SELECT * FROM post p
            WHERE p.userId = :userId;
            """)
    List<PostEntity> getByUserId(@Param("userId") int userId);
}