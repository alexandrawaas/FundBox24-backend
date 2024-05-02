package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;    // TODO: Random generation of username
    @NotNull
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @NotNull
    @NotBlank(message = "Password cannot be empty")     // TODO: Password validation
    private String password;

    public User(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User()
    {

    }
}
