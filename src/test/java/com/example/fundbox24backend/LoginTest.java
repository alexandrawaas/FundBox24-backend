package com.example.fundbox24backend;

import com.example.fundbox24backend.api.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoginTest {

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    /**
     * Test if the user can log in with BasicAuth
     */
    @Test()
    void login() {
        userController.login();
    }
}
