package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull @NotBlank(message="category name must not be empty.")
    @Size(min=1, max=30, message="title must have between {min} and {max} characters.")
    private String name;
    private ValueType value = ValueType.LOW;

    public Category()
    {

    }

    public Boolean requiresAction() {
        return value == ValueType.HIGH;
    }

    public Category(String name, ValueType value) {
        this.name = name;
        this.value = value;
    }
}