package com.example.demo.repository;

import com.example.demo.repository.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.Optional;

@Slf4j
public class UserRepositoryDBImpl  {

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
}
