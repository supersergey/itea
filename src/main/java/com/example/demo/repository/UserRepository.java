package com.example.demo.repository;

import com.example.demo.repository.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

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
    """, nativeQuery = true)
    List<String> findUsersLastNamesWithTheBiggestNumberOfPosts();

    @Query(value = """
                select u, count(p) as c from user u
                join post p on u = p.user
                group by u
                order by c desc
                limit 1
            """)
    User findUserWithMaximalNumberOfPosts();

}
