package com.revature.revpay.controllers;

import com.revature.revpay.entities.Transaction;
import com.revature.revpay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TransactionController {
//    private final TransactionService transactionService;
//
//    @Autowired
//    public TransactionController(TransactionService transactionService) {
//        this.transactionService = transactionService;
//    }
//
//    @PostMapping(path = "/transactions/send")
//    public ResponseEntity<Transaction> sendMoney(@RequestBody Transaction transaction) {
//        System.out.println("SENDING MONEY " + transaction);
//        Transaction savedTransaction = this.transactionService.sendMoney(transaction);
//        return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/transactions/request")
//    public ResponseEntity<Transaction> requestMoney(@RequestBody Transaction transaction) {
//        this.transactionService.requestMoney(transaction);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PutMapping(path = "/transactions/{id}")
//    public ResponseEntity<Transaction> approveRequestedMoney(@PathVariable String id) {
//        Optional<Transaction> found = this.transactionService.approveRequestedMoney(id);
//        return found.map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.OK));
//    }
}
