package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public User getUser() {
        //TODO: Implement with token
        return userRepository.findAll().iterator().next();
    }

    @PutMapping("/user")
    public @ResponseBody User updateUser(@RequestBody String name, @RequestBody String email, @RequestBody String password, @RequestBody String role) {
        //TODO: Implement with token
        return userRepository.findAll().iterator().next();
    }

    @PostMapping("/login")
    public @ResponseBody User login(@RequestBody String email, @RequestBody String password) {
        // TODO: Implement with token
        return null;
    }

    @PostMapping("/logut")
    public @ResponseBody User logout() {
        //TODO: Implement with token
        return null;
    }

    @PostMapping("/register")
    public @ResponseBody User createUser(@RequestBody String name, @RequestBody String email, @RequestBody String password) {
        User user = new User(name, email, password);
        userRepository.save(user);
        return user;
    }

}
