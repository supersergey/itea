package com.example.demo.repository;

import com.example.demo.repository.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;

@Qualifier("springDataUserRepository")
public interface UserRepository extends Repository<User, Integer> {
    User findById(int id);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    User save(User user);
    int count();
    Collection<User> findAll();

    @Query("""
                select u.last_name from "user" u
                join post p on u.id = p.user_id
                group by u.first_name, u.last_name
                order by count(p.id) desc limit 1
                """)
    String getUserLastNameWithMaxPosts();
}
