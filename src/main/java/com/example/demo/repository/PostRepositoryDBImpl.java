package com.example.demo.repository;

import com.example.demo.repository.model.Post;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Override
    public List<Post> getByUserId(int userId) {
        List<Post> posts = new ArrayList<>();
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
                posts.add(new Post(
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

    @Override
    public Post save (Post post)
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
                    return new Post(
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

    @Override
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

    @Override
    public Post getById(int postId) {
        List<Post> posts = new ArrayList<>();
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
                return new Post(
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

    @Override
    public Collection<Post> findAll()
    {
        List<Post> posts = new ArrayList<>();
        String query = """
                select * from post""";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
            {
                while (resultSet.next()) {
                    posts.add(new Post(
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

    @Override
    public int edit(int postId, Post post)
    {
        return 0;
    }

    @Override
    public void delete(int postId)
    {}
}
