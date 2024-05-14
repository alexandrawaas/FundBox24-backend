package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.model.Category;
import com.example.fundbox24backend.api.repository.CategoryRepository;
import com.example.fundbox24backend.api.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class CategoryController
{

    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
