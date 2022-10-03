package com.example.onliner.repository.impl;

import com.example.onliner.domain.Category;
import com.example.onliner.domain.exception.CustomNotFoundException;
import com.example.onliner.repository.CategoryRepository;
import com.example.onliner.repository.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static com.example.onliner.repository.query.CategoryQuery.*;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final int RETURN_VALUE = 1;
    private final JdbcOperations jdbcOperations;

    @Autowired
    public CategoryRepositoryImpl(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public long create(Category category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKeys() != null ? (Long)keyHolder.getKeys().get("id") : -1L;
    }

    @Override
    public Category findById(long id) {
        return jdbcOperations.query(SELECT_CATEGORY_BY_ID, new CategoryMapper(), id)
                .stream()
                .findFirst()
                .orElseThrow(CustomNotFoundException :: new);
    }

    @Override
    public List<Category> showAll() {
        return jdbcOperations.query(SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    public boolean delete(long categoryId) {
        return jdbcOperations.update(DELETE_CATEGORY, categoryId) == RETURN_VALUE;
    }
}
