package com.example.onliner.repository.mapper;

import com.example.onliner.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_DESCRIPTION = "description";

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong(PRODUCT_ID));
        product.setName(rs.getString(PRODUCT_NAME));
        product.setPrice(rs.getBigDecimal(PRODUCT_PRICE));
        product.setDescription(rs.getString(PRODUCT_DESCRIPTION));
        return product;
    }
}
