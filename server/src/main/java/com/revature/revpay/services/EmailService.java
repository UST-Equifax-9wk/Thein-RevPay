package com.revature.revpay.services;

import com.revature.revpay.dto.EmailDto;
import com.revature.revpay.entities.BankAccount;
import com.revature.revpay.entities.Transaction;
import com.revature.revpay.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final UserService userService;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public void sendEmail(EmailDto emailDto) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDto.getTo());
        email.setSubject(emailDto.getSubject());
        email.setText(emailDto.getText());
        System.out.println(emailDto);
        javaMailSender.send(email);
    }
}
