package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public @ResponseBody User createUser(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // TODO: Encrypt password
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    @GetMapping("/user")
    public @ResponseBody Iterable<User> getUser() {
        return userRepository.findAll();
    }

}
