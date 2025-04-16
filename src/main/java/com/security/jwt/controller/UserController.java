package com.security.jwt.controller;

import com.security.jwt.entity.User;
import com.security.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-new-user")
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }
}
