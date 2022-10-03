package com.example.onliner.service.impl;

import com.example.onliner.domain.Category;
import com.example.onliner.domain.exception.CustomNotFoundException;
import com.example.onliner.repository.CategoryRepository;
import com.example.onliner.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category create(Category category) {
        categoryRepository.create(category);
        return category;
    }

    @Override
    @Transactional
    public List<Category> retrieveAll() {
        return categoryRepository.showAll();
    }

    @Override
    @Transactional
    public Category retrieveById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if(!categoryRepository.delete(id)){
            throw new CustomNotFoundException("Not found a category with id = " + id);
        }
    }


}
