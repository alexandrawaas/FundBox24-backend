package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.controller.exceptions.ChatNotFoundException;
import com.example.fundbox24backend.api.model.Category;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.model.Message;
import com.example.fundbox24backend.api.repository.CategoryRepository;
import com.example.fundbox24backend.api.repository.ChatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class CategoryController
{

    private final CategoryRepository repository;

    CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/categories")
    List<Category> getAllCategories() {
        return repository.findAll();
    }
}
