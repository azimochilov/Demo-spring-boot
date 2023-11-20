package com.example.demo.rest;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResource {

    private  final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }
}
