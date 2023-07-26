package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    @Query("""
                select p from post p
                where p.user.lastName = :lastName
            """)
    Collection<PostEntity> findByUserFirstName(String lastName);
}
