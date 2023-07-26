package com.example.demo.repository;

import com.example.demo.repository.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;

@Qualifier("springDataUserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    Collection<User> findByPostsTitle(String title);

    Collection<User> findAllByFirstNameOrderByIdDesc(String firstName);

    @Query(value = """
            select  u, count(p) as c from User u
            join post p on p.user = u
            group by u
            order by c desc
            limit 1
            """
    )
    User findUserWithMaximalAmountOfPosts();
}
