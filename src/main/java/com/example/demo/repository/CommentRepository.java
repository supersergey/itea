package com.example.demo.repository;

import com.example.demo.repository.model.CommentEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Qualifier("springDataCommentRepository")
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
    List<CommentEntity> findByPostEntityId(int postId);
    List<CommentEntity> findByUserId(int userId);
}
