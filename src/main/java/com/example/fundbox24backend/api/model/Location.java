package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
public class Location
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private double latitude;
    private double longitude;
}
