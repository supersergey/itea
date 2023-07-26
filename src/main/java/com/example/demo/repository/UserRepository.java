package com.example.demo.repository;

import com.example.demo.repository.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

@Qualifier("springDataUserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = """
            select u from user u
            join post p on u = p.user
            where p.title = :title
            """)
    Collection<User> findByPostsTitle(String title);

    @Query(value = """
                select u, count(p) as c from user u
                join post p on u = p.user
                group by u
                order by c desc
                limit 1
            """)
    User findUserWithMaximalNumberOfPosts();
}
