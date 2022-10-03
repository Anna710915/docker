package com.example.onliner.controller.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CategoryDto {

    private long id;

    @Pattern(regexp = "\\w+", message = "The name should contain only symbols.")
    @Size(min = 2, max = 50, message = "The size should be between 2 and 50 symbols")
    private String name;

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
}
