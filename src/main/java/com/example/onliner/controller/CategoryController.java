package com.example.onliner.controller;

import com.example.onliner.controller.dto.CategoryDto;
import com.example.onliner.domain.Category;
import com.example.onliner.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.create(categoryBuilder(categoryDto));
        return categoryDtoBuilder(category);
    }

    @GetMapping(value="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAll() {
        return categoryDtoListBuilder(categoryService.retrieveAll());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getById(@PathVariable long id) {
        return categoryDtoBuilder(categoryService.retrieveById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        categoryService.delete(id);
    }

    private List<CategoryDto> categoryDtoListBuilder(List<Category> categories) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categories){
            categoryDtoList.add(categoryDtoBuilder(category));
        }
        return categoryDtoList;
    }

    private Category categoryBuilder(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }

    private CategoryDto categoryDtoBuilder(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
