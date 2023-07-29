package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    List<PostEntity> findByUserId(int userId);

    int countByUserId(int userId);

    @Query(value = """
    select user_id
                 from (select user_id, count(user_id) as num_of_posts
                       from post
                       group by user_id) as num_of_user_posts
                 where num_of_posts =
                       (select max(amount_of_posts)
                        from (select user_id, count(user_id) as amount_of_posts
                              from post
                              group by user_id) as amount_of_user_posts)
    """, nativeQuery = true)
    List<Integer> findUsersIdsWithTheBiggestNumberOfPosts();

    @Query(value = """
            select p.user from post p
            where p.title = :title
            """)
    Collection<User> findUserByPostTitle(String title);

}
