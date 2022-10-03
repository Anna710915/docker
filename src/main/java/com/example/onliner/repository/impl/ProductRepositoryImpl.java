package com.example.onliner.repository.impl;

import com.example.onliner.domain.Product;
import com.example.onliner.repository.ProductRepository;
import com.example.onliner.repository.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static com.example.onliner.repository.query.ProductQuery.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public ProductRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public long create(Product product, long userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setLong(4, userId);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKeys() != null ? (Long)keyHolder.getKeys().get("id") : -1L;
    }

    @Override
    public void insertProductCategoryKeys(long productId, long categoryId) {
        jdbcOperations.update(INSERT_PRODUCT_CATEGORIES_KEYS, productId, categoryId);
    }

    @Override
    public List<Product> findAll() {
        return jdbcOperations.query(SELECT_PRODUCTS, new ProductMapper());
    }

    @Override
    public Optional<Product> findById(long id) {
        return jdbcOperations.query(SELECT_PRODUCT_BY_ID, new ProductMapper(), id)
                .stream().findFirst();
    }

    @Override
    public boolean delete(long id) {
        return jdbcOperations.update(DELETE_PRODUCT_BY_ID, id) == 1;
    }
}
