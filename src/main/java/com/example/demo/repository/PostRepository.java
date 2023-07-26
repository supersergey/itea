package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Qualifier("springDataPostRepository")
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    List<PostEntity> getByUserId(int userId);
    int countByUserId(int userId);
}
