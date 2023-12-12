package com.revature.RevPay.controllers;

import com.revature.RevPay.entities.BankAccount;
import com.revature.RevPay.entities.Customer;
import com.revature.RevPay.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/customers/sign-up")
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PostMapping(path = "/customers/{customerId}/bank-accounts")
    public BankAccount createBankAccount(@PathVariable(name = "customerId") String customerId) {
        customerService.createBankAccount(String customerId);
    }
}
