package com.revature.revpay.controllers;

import com.revature.revpay.dto.NewUserDto;
import com.revature.revpay.entities.Credential;
import com.revature.revpay.entities.User;
import com.revature.revpay.services.AuthService;
import com.revature.revpay.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<User> signUp(@RequestBody  NewUserDto newUserDto, HttpServletResponse httpServletResponse) {
        System.out.println("Request body: " + newUserDto);
        Credential credential = newUserDto.getCredential();
        String username = credential.getUsername();
        authService.saveCredential(credential);
        Cookie cookie = authService.createCookieWithUsername(username);
        User savedUser = userService.saveUser(newUserDto.getUser());
        httpServletResponse.addCookie(cookie);
        return new ResponseEntity<User>(savedUser, HttpStatus.OK);
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<User> signIn(@RequestBody Credential credential, HttpServletResponse httpServletResponse) {
        boolean isAuthenticated = authService.authenticateUser(credential);
        System.out.println("Authenticated: " + isAuthenticated);
        if(isAuthenticated) {
            String username = credential.getUsername();
            Cookie cookie = authService.createCookieWithUsername(username);
            httpServletResponse.addCookie(cookie);
            Optional<User> found = userService.findByUsername(username);
            return found.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
