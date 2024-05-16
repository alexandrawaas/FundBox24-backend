package com.example.fundbox24backend.api.service.exceptions;

public class CategoryNotFoundException extends RuntimeException
{
    public CategoryNotFoundException()
    {
        super("Could not find category");
    }
}
