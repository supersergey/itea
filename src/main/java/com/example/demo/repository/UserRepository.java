package com.example.demo.repository;

import com.example.demo.repository.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

//    @Query(value = """
//                select u, count(p) as c from "user" u
//                join post p on u = p.user
//                group by u
//                order by c desc
//                limit 1
//            """)
//    UserEntity findUserWithMaximalNumberOfPosts();
//
//    @Query("""
//            UPDATE user u
//            set u.age = :age, u.first_name = :name, u.last_name = :lastName
//            WHERE u.id = :id
//            """)
//    void update(@Param("id") int userId, @Param("name") String userName,
//                @Param("lastName") String userLastName, @Param("age") int userAge);
}