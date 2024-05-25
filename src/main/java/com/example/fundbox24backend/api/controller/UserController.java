package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoResponse;
import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportDtoResponse;
import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportDtoResponse;
import com.example.fundbox24backend.api.datatransfer.user.AuthDtoRequest;
import com.example.fundbox24backend.api.datatransfer.user.UserDtoRequest;
import com.example.fundbox24backend.api.datatransfer.user.UserDtoResponse;
import com.example.fundbox24backend.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public UserDtoResponse getUser() {
        return userService.getCurrentUser();
    }

    @PutMapping("/user")
    public @ResponseBody UserDtoResponse updateUser(@RequestBody UserDtoRequest userDtoRequest) {
        //TODO: Implement with token
        return userService.updateUser(userDtoRequest);
    }

    @GetMapping("/user/report/found")
    public List<FoundReportDtoResponse> getUserFoundReports() {
        //TODO: Implement with token
        return userService.getUserFoundReports();
    }

    @GetMapping("/user/report/lost")
    public List<LostReportDtoResponse> getUserLostReports() {
        //TODO: Implement with token
        return userService.getUserLostReports();
    }

    @GetMapping("/user/chat")
    public List<ChatDtoResponse> getUserChats() {
        //TODO: Implement with token
        return userService.getUserChats();
    }

    @PostMapping("/login")
    public @ResponseBody UserDtoResponse login(@RequestBody AuthDtoRequest authDtoRequest) {
        return userService.login(authDtoRequest);
    }

    @PostMapping("/logut")
    public void logout() {
        userService.logout();
    }

    @PostMapping("/register")
    public @ResponseBody UserDtoResponse createUser(@RequestBody AuthDtoRequest authDtoRequest) {
        return userService.register(authDtoRequest);
    }

}
