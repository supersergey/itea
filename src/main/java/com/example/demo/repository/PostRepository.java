package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.List;

@Qualifier("springDataPostRepository")
public interface PostRepository extends CrudRepository<PostEntity, Integer>{
    List<PostEntity> getByUserId(int userId);
    //void delete(int postId);
    PostEntity save(PostEntity post);
    int countByUserId(int userId);
    PostEntity getById (int postId);
    //int edit(int postId, PostEntity post);
    Collection<PostEntity> findAll();
}
