package com.example.onliner.service;

import com.example.onliner.domain.Category;
import com.example.onliner.domain.Product;

import java.util.List;


public interface ProductService {

    void create(Product product, List<Category> categoryList, long userId);
    List<Product> retrieveAll();
    Product retrieveById(long id);
    void delete(long id);
}
