package com.example.demo.rest;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserResource {

    private  final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        if(!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("Password's length less than 4 ", HttpStatus.BAD_REQUEST);
        }
        if(userService.checkUserName(user.getUserName())){
            return new ResponseEntity(" This user already exsists! ", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userService.create(user));
    }

    private Boolean checkPasswordLength(String password){

        return (password.length()>4);
    }
}
