package com.example.onliner.service;

import com.example.onliner.domain.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);
    void delete(long id);
    Category retrieveById(long id);
    List<Category> retrieveAll();
}
