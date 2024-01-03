package com.revature.revpay.controllers;

import com.revature.revpay.entities.BankAccount;
import com.revature.revpay.entities.Loan;
import com.revature.revpay.entities.Transaction;
import com.revature.revpay.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping(path = "/users/{userId}/bank-accounts")
    public ResponseEntity<List<BankAccount>> getBankAccounts(@PathVariable String userId) {
        List<BankAccount> bankAccounts = bankAccountService.findAllByAccountHolderId(userId);
        return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{userId}/bank-accounts/{bankAccountId}")
    public ResponseEntity<BankAccount> getBankAccount(@PathVariable String userId, @PathVariable String bankAccountId) {
        Optional<BankAccount> bankAccount = bankAccountService.findById(bankAccountId);

        return bankAccount.map(account -> new ResponseEntity<>(account, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/users/{userId}/bank-accounts")
    public ResponseEntity<BankAccount> openNewAccount(@PathVariable String userId, @RequestBody BankAccount bankAccount) {
        System.out.println(bankAccount.toString());
        BankAccount newBankAccount = this.bankAccountService.openNewAccount(userId, bankAccount);
        return new ResponseEntity<>(newBankAccount, HttpStatus.OK);
    }

    @DeleteMapping(path = "/bank-accounts/{id}")
    public void closeBankAccount(@PathVariable String id) {
        this.bankAccountService.closeBankAccount(id);
    }


    @PostMapping(path = "/transactions/send")
    public ResponseEntity<Transaction> sendMoney(@RequestBody Transaction transaction) {
        System.out.println("SENDING MONEY " + transaction);
        Transaction savedTransaction = this.bankAccountService.sendMoney(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
    }

    @PostMapping(path = "/transactions/request")
    public ResponseEntity<Transaction> requestMoney(@RequestBody Transaction transaction) {
        this.bankAccountService.requestMoney(transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/transactions/{id}")
    public ResponseEntity<Transaction> approveRequestedMoney(@PathVariable String id) {
        Optional<Transaction> found = this.bankAccountService.approveRequestedMoney(id);
        return found.map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.OK));
    }

    @DeleteMapping(path = "/transactions/{id}")
    public ResponseEntity<Transaction> declineRequestedMoney(@PathVariable String id) {
        this.bankAccountService.declineRequestedMoney(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
