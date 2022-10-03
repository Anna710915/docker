package com.example.onliner.repository.query;

public final class CategoryQuery {

    public static final String INSERT_CATEGORY = """
            INSERT INTO categories (name)
            VALUES (?);
            """;

    public static final String DELETE_CATEGORY = """
            DELETE FROM categories
            WHERE id = (?);
            """;

    public static final String SELECT_CATEGORY_BY_ID = """
            SELECT id, name FROM categories
            WHERE id = (?)""";

    public static final String SELECT_CATEGORY_BY_PRODUCT_ID = """
            SELECT categories.id, name FROM categories
            JOIN products_categories ON products_categories.categories_id = categories.id
            JOIN products ON products_categories.product_id = product.id
            WHERE product.id = (?)""";

    public static final String SELECT_ALL_CATEGORIES = """
            SELECT id, name FROM categories;
            """;
}
