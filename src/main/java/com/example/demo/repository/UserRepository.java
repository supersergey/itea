package com.example.demo.repository;

import com.example.demo.repository.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.List;

@Qualifier("springDataUserRepository")
public interface UserRepository extends Repository<User, Integer> {

    User findById(int id);
    boolean existsById(int id);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    User save(User user);

    int count();

    Collection<User> findAll();

    @Query("""
    select distinct u.last_name
    from "user" u
             join post p on u.id = p.user_id
    group by u.id
    having count(p.id) =
           (select count(id) as max_post_count
            from post
            group by user_id
            order by max_post_count DESC
            limit 1)
    """)
    List<String> findUsersLastNamesWithTheBiggestNumberOfPosts();
}
