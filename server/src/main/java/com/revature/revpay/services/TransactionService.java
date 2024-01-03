package com.revature.revpay.services;

import com.revature.revpay.entities.Transaction;
import com.revature.revpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(
            TransactionRepository transactionRepository
    ) {
        this.transactionRepository = transactionRepository;
    }


    public Transaction save(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }


    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }
}
