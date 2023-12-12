package com.revature.RevPay.services;

import com.revature.RevPay.entities.BankAccount;
import com.revature.RevPay.entities.Customer;
import com.revature.RevPay.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BankAccountService bankAccountService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BankAccountService bankAccountService) {
        this.customerRepository = customerRepository;
        this.bankAccountService = bankAccountService;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public BankAccount createBankAccount(String customerId) {
        Optional<Customer> customer = customerRepository.findById(Integer.valueOf(customerId));
        customer.ifPresent(bankAccountService::createBankAccountForCustomer);
    }
}
