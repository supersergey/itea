package com.example.demo.repository;

import com.example.demo.repository.model.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("""
            select u.last_name
            from user u
            where u.id =\s
                (select user_id from (
                        select count(u.last_name) count, p.user_id
                        from user u
                        join post p on p.user_id = u.id
                        group by p.user_id
                        order by count desc
                        limit 1) as foo) foo_2
            """)
    String lastNameOfUserWithTheMostNumberOfPosts();

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    @Query("""
            UPDATE user
            set age = :age, first_name = :name, last_name = :lastName
            WHERE user.id = :id
            """)
    void update(@Param("id") int userId, @Param("name") String userName,
                        @Param("lastName") String userLastName, @Param("age") int userAge);
}