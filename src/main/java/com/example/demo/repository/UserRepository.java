package com.example.demo.repository;

import com.example.demo.repository.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.Repository;

import java.util.Collection;

@Qualifier("springDataUserRepository")
public interface UserRepository extends Repository<User, Integer> {

    User findById(int id);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    User save(User user);

    int count();

    Collection<User> findAll();

    void delete(int userId);

    void update(int userId, User user);
}
