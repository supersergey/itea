package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepositoryInMemoryImpl implements PostRepository {
    private final Map<Integer, PostEntity> posts = new ConcurrentHashMap<>();

    private int counter = 0;

    private int nextId() {
        return counter++;
    }

    public List<PostEntity> getByUserId(int userId) {
        return posts.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(post -> userId == post.getUser().getId())
                .toList();
    }

    public PostEntity save(PostEntity post) {
        int id = nextId();
        posts.put(id, post);
        return post;
    }

    public void delete(int postId) {
        posts.remove(postId);
    }

    public int countByUserId(int userId) {

        return (int) posts.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .map(PostEntity::getUser)
                .filter(user -> user.getId() == userId).count();
    }

    public PostEntity getById(int postId) {
        return posts.get(postId);
    }

    public int edit(int postId, PostEntity updatedPost) {
        posts.put(postId, updatedPost);
        return postId;
    }

    @Override
    public <S extends PostEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PostEntity> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    public Collection<PostEntity> findAll() {
        return posts.values();
    }

    @Override
    public Iterable<PostEntity> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(PostEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends PostEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
