package com.revature.revpay.controllers;

import com.revature.revpay.dto.EmailDto;
import com.revature.revpay.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(path = "/emails")
    public void sendEmail(@RequestBody EmailDto emailDto) {
        this.emailService.sendEmail(emailDto);
    }
}
