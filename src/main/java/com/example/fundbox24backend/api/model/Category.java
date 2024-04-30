package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private ValueType value;
    private String imagePath;

    public Category()
    {

    }

    public Boolean requiresAction() {
        return value == ValueType.HIGH;
    }

    public Category(String name, ValueType value, String imagePath) {
        this.name = name;
        this.value = value;
        this.imagePath = imagePath;
    }
}