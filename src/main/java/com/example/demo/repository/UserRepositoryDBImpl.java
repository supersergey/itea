package com.example.demo.repository;

import com.example.demo.repository.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;

@Slf4j
public class UserRepositoryDBImpl {

    private final Connection connection;

    public UserRepositoryDBImpl() {
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

    public User findById(int id) {
        String query = String.format("""
                        select * from "user"
                        where id = %d
                    """, id);
        try(
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                return new User(
                        id,
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Collections.emptyList(),
                        Collections.emptyList()
                );
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        String query = String.format("""
                select * from "user" where first_name = '%s' and last_name = '%s'
                """, firstName, lastName);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet.next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public User save(User user) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("""
                    insert into "user" (first_name, last_name)
                    values ('%s', '%s')
                    """,
                    user.getFirstName(), user.getLastName()
            );
            var updatedCount = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            if (updatedCount != 1) {
                throw new RuntimeException("User table update failed");
            } else {
                ResultSet generated = statement.getGeneratedKeys();
                if (generated.next()) {
                    return new User(
                            generated.getInt("id"),
                            user.getFirstName(),
                            user.getLastName(),
                            Collections.emptyList(),
                            Collections.emptyList()
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int count() {
        String query = """
                select count(*) from "user"
                """;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Collection<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = """
                select * from "user"
                """;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Collections.emptyList(),
                        Collections.emptyList()
                ));
            }
            return users;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
