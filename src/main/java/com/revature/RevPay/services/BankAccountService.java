package com.revature.RevPay.services;

import com.revature.RevPay.entities.BankAccount;
import com.revature.RevPay.entities.Customer;
import com.revature.RevPay.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createBankAccountForCustomer(Customer customer) {
        return bankAccountRepository.save(customer);
    }
}
