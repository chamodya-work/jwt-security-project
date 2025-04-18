package com.security.jwt.controller;

import com.security.jwt.entity.User;
import com.security.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register-new-user")
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostConstruct//this method automatically run ,when application start
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @GetMapping("/for-admin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "this is accessible for admin only";
    }

    @GetMapping("/for-user")
    @PreAuthorize("hasAnyRole('User','Admin')")
    public String forUser() {
        return "this can accessible for both admin and user";
    }

}
