package com.revature.revpay.controllers;

import com.revature.revpay.entities.Loan;
import com.revature.revpay.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(path = "/loans")
    public ResponseEntity<Loan> applyForLoan(@RequestBody Loan loan) {
        Loan applied = this.loanService.applyForLoan(loan);
        return new ResponseEntity<>(applied, HttpStatus.OK);
    }
}
