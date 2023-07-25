package com.example.demo.repository;

import com.example.demo.repository.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Collection;
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
        return false;
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
        return 0;
    }

    public Collection<User> findAll() {
        return null;
    }
}
