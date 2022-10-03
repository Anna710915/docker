package com.example.onliner.repository;

import com.example.onliner.domain.Category;

import java.util.List;

public interface CategoryRepository {

    long create(Category category);

    boolean delete(long categoryId);

    Category findById(long id);

    List<Category> showAll();

}
