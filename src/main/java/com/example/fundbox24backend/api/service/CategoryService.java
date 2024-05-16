package com.example.fundbox24backend.api.service;

import com.example.fundbox24backend.api.model.Category;
import com.example.fundbox24backend.api.repository.CategoryRepository;
import com.example.fundbox24backend.api.service.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService
{
    private final CategoryRepository repository;

    CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getCategory(Long categoryId)
    {
        return repository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }
    public Category createCategory(Category category)
    {
        return repository.save(category);
    }
}
