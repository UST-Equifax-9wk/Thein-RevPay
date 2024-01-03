package com.revature.revpay.controllers;

import com.revature.revpay.entities.Admin;
import com.revature.revpay.entities.Credential;
import com.revature.revpay.entities.Loan;
import com.revature.revpay.services.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(path = "/admin-sign-in")
    public ResponseEntity<Admin> signIn(@RequestBody Credential credential, HttpServletResponse response) {
       Optional<Admin> authenticated = adminService.signIn(credential);
       if (authenticated.isPresent()) {
           Cookie c = adminService.createCookieWithUsername(credential.getUsername());
           response.addCookie(c);
           return new ResponseEntity<>(authenticated.get(), HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }
    }

    @GetMapping(path ="/admins/{username}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable String username) {
        Optional<Admin> found = this.adminService.findByUsername(username);
        return found.map(admin -> new ResponseEntity<>(admin, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/loans/{loanId}")
    public void approveLoan(@RequestBody Loan loan) {
        this.adminService.approveLoan(loan);
    }
}
