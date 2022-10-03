package com.example.onliner.repository.mapper;

import com.example.onliner.domain.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    private static final String CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "name";
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong(CATEGORY_ID));
        category.setName(rs.getString(CATEGORY_NAME));
        return category;
    }
}
