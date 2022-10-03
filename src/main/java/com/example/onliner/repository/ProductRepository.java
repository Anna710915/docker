package com.example.onliner.repository;

import com.example.onliner.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    long create(Product product, long userId);

    void insertProductCategoryKeys(long product_id, long category_id);

    List<Product> findAll();

    Optional<Product> findById(long id);

    boolean delete(long id);
}
