package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class ImageMessage extends Message
{
    private String imagePath;
}