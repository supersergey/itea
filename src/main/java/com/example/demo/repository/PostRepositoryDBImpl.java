package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
public class PostRepositoryDBImpl implements PostRepository{
    private final Connection connection;
    public PostRepositoryDBImpl()
    {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres"
            );
            log.info("Successfully established JDBC connection");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<PostEntity> getByUserId(int userId) {
        List<PostEntity> posts = new ArrayList<>();
        String query = String.format("""
                        select * from post
                        where user_id = %d  
                    """, userId);
        try(
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        )
        {
            while (resultSet.next()) {
                posts.add(new PostEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("body"),
                        resultSet.getInt("user_id")
                ));
            }
            return posts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public PostEntity save (PostEntity post)
    {
        String query = String.format("""
                    insert into post (title, body, user_id)
                    values ('%s', '%s', '%d')
                    """,
            post.getTitle(), post.getBody(), post.getUserId()
        );
        try (Statement statement = connection.createStatement()) {

            var updatedCount = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            if (updatedCount != 1) {
                throw new RuntimeException("Post table update failed");
            } else {
                ResultSet generated = statement.getGeneratedKeys();
                if (generated.next()) {
                    return new PostEntity(
                            generated.getInt("id"),
                            post.getTitle(),
                            post.getBody(),
                            post.getUserId()
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public int countByUserId(int userId)
    {
        String query = String.format("""
                select count(*) from post
                where user_id %d
                """, userId);
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
        {
            if (resultSet.next())
            {
                return resultSet.getInt(1);
            }
            else return 0;
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public PostEntity getById(int postId) {
        List<PostEntity> posts = new ArrayList<>();
        String query = String.format("""
                        select * from post
                        where id = %d  
                    """, postId);
        try(
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        )
        {
            if (resultSet.next()) {
                return new PostEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("body"),
                        resultSet.getInt("user_id")
                );
            }
            else return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public <S extends PostEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }


    public Optional<PostEntity> findById(Integer integer) {
        return Optional.empty();
    }


    public boolean existsById(Integer integer) {
        return false;
    }

    public Collection<PostEntity> findAll()
    {
        List<PostEntity> posts = new ArrayList<>();
        String query = """
                select * from post""";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
            {
                while (resultSet.next()) {
                    posts.add(new PostEntity(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("body"),
                            resultSet.getInt("user_id")
                    ));
                }
                return posts;
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Iterable<PostEntity> findAllById(Iterable<Integer> integers) {
        return null;
    }

    public long count() {
        return 0;
    }

    public void deleteById(Integer integer) {

    }

    public void delete(PostEntity entity) {

    }

    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    public void deleteAll(Iterable<? extends PostEntity> entities) {

    }

    public void deleteAll() {

    }

    public int edit(int postId, PostEntity post)
    {
        return 0;
    }


    public void delete(int postId)
    {

    }
}
