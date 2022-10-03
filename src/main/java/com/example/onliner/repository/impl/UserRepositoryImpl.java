package com.example.onliner.repository.impl;

import com.example.onliner.domain.User;
import com.example.onliner.repository.UserRepository;
import com.example.onliner.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Formatter;
import java.util.Optional;

import static com.example.onliner.repository.query.CategoryQuery.INSERT_CATEGORY;
import static com.example.onliner.repository.query.UserQuery.INSERT_USER;
import static com.example.onliner.repository.query.UserQuery.SELECT_USER_BY_USERNAME;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public long create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDate(6, Date.valueOf(user.getDateOfBirth().toString()));
            preparedStatement.setString(7, user.getRole().toString());
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKeys() != null ? (Long)keyHolder.getKeys().get("id") : -1L;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jdbcOperations.query(SELECT_USER_BY_USERNAME, new UserMapper(), username).stream().findAny();
    }
}

