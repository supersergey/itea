package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    @Query(value = """
            select p.user from post p
            where p.title = :title
            """)
    Collection<User> findUserByPostTitle(String title);
}
