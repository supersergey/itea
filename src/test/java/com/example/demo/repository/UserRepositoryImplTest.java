package com.example.demo.repository;

import com.example.demo.controller.dto.User;
import com.example.demo.repository.model.Convertors.UserAndUserEntityConvertor;
import org.junit.jupiter.api.Test;

class UserRepositoryImplTest {

//    private final UserRepository repository = new UserRepositoryDBImpl();
//    private final UserAndUserEntityConvertor convertor = new UserAndUserEntityConvertor();
//
//    @Test
//    void shouldReturnAUserById() {
//        var user = new User("John", "Smith");
//        var userId = repository.save(convertor.toEntity(user)).getId();
//
//        var actual = repository.findById(userId).get();
//        assert user.equals(convertor.toDto(actual)); // Exception виникне, якщо умова = false
//    }
//
//    @Test
//    void shouldReturnNullIfUserNotFound() {
//        var actual = repository.findById(-1);
//
//        assert actual.isEmpty(); // Exception виникне, якщо умова = false
//    }
}