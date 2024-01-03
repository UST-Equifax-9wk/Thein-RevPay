package com.revature.revpay.controllers;

import com.revature.revpay.entities.User;
import com.revature.revpay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/users")
    public ResponseEntity<List<User>> searchAllByUsernameOrEmail(@RequestParam String username, @RequestParam String email) {
        return new ResponseEntity<>(userService.findAllByUsernameOrEmail(username, email), HttpStatus.OK);
    }
}
