package com.example.onliner.repository.query;

public final class ProductQuery {

    public static final String INSERT_PRODUCT = """
            INSERT INTO products (name, price, description, user_id)
            VALUES (?, ?, ?, ?);
            """;

    public static final String INSERT_PRODUCT_CATEGORIES_KEYS = """
            INSERT INTO products_categories(product_id, categories_id)
            VALUES (?, ?);
            """;

    public static final String SELECT_PRODUCTS = """
            SELECT id, name, price, description FROM products
            """;

    public static final String SELECT_PRODUCT_BY_ID = """
            SELECT id, name, price, description FROM products
            WHERE id = (?);
            """;

    public static final String DELETE_PRODUCT_BY_ID = """
            DELETE FROM products WHERE id = (?);
            """;
}
