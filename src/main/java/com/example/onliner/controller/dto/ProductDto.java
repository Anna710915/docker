package com.example.onliner.controller.dto;

import com.example.onliner.domain.Category;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private long id;

    @Pattern(regexp = "\\w+", message = "The name should contain only symbols.")
    @Size(min = 2, max = 50, message = "The size should be between 2 and 50 symbols")
    private String name;

    @Min(value = 5, message = "The price is less than 5")
    @Max(value = 100000, message = "The price is more than 100000")
    private BigDecimal price;

    @Pattern(regexp = "[^<>]+", message = "Invalid description. Restrictions: <>")
    @Size(max = 1000, message = "The description is more than 1000 symbols")
    private String description;
    private List<Category> categories;

    public ProductDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
