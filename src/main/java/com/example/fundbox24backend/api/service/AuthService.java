package com.example.fundbox24backend.api.service;

import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.UserRepository;

public class AuthService
{
    private UserRepository userRepository;

    public User getCurrentUser()
    {
        // TODO: implement getCurrentUser
        return userRepository.findAll().getFirst();
    }
}
