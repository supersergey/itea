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
    select distinct user_posts_stats.last_name
    from (select p.user_id, u.last_name, count(user_id) as num_of_posts
          from post p
                   join "user" u on u.id = p.user_id
          group by p.user_id, u.last_name) as user_posts_stats
    where num_of_posts =
          (select max(amount_of_posts)
           from (select p.user_id, u.last_name, count(*) as amount_of_posts
                 from post p
                          join "user" u on u.id = p.user_id
                 group by p.user_id, u.last_name) as posts_stats)
    """)
    List<String> findUsersLastNamesWithTheBiggestNumberOfPosts();
}
