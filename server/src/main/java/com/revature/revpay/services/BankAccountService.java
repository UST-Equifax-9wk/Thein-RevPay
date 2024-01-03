package com.revature.revpay.services;

import com.revature.revpay.entities.*;
import com.revature.revpay.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private final UserService userService;
    private final BankAccountRepository bankAccountRepository;
    private final CardService cardService;
    private final TransactionService transactionService;
    @Autowired
    public BankAccountService(
            UserService userService,
            BankAccountRepository bankAccountRepository,
            CardService cardService,
            TransactionService transactionService
    ) {
        this.userService = userService;
        this.bankAccountRepository = bankAccountRepository;
        this.cardService = cardService;
        this.transactionService = transactionService;
    }

    public List<BankAccount> findAllByAccountHolderId(String accountHolderId) {
        Optional<User> accountHolder = userService.findById(accountHolderId);

        if (accountHolder.isPresent()) {
            return bankAccountRepository.findAllByAccountHolder(accountHolder.get());
        } else {
            throw new ObjectNotFoundException(User.class, "User");
        }
    }

    public Optional<BankAccount> findById(String bankAccountId) {
        return bankAccountRepository.findById(Long.valueOf(bankAccountId));
    }

    public void deductMoney(Transaction transaction) {
        BankAccount bankAccount = transaction.getSenderAccount();
        Double amount = transaction.getAmount();
        Long bankAccountId = bankAccount.getId();
        Optional<BankAccount> found = findById(bankAccountId);
        if (found.isPresent()) {
            BankAccount foundAccount = found.get();
            Double balance = foundAccount.getBalance();
            Double newBalance = balance - amount;
            foundAccount.setBalance(newBalance);
            save(foundAccount);
        }
    }

    public void addMoney(Transaction transaction) {
        BankAccount bankAccount = transaction.getReceiverAccount();
        Double amount = transaction.getAmount();
        Long bankAccountId = bankAccount.getId();
        Optional<BankAccount> found = findById(bankAccountId);
        if (found.isPresent()) {
            BankAccount foundAccount = found.get();
            Double balance = foundAccount.getBalance();
            Double newBalance = balance + amount;
            foundAccount.setBalance(newBalance);
            save(foundAccount);
        }
    }

    public BankAccount openNewAccount(String userId, BankAccount bankAccount) {
        Optional<User> found = this.userService.findById(userId);
        found.ifPresent(bankAccount::setAccountHolder);
        return this.bankAccountRepository.save(bankAccount);
    }

    public void save(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    public Optional<BankAccount> findById(Long id) {
        return bankAccountRepository.findById(id);
    }

    @Transactional
    public void closeBankAccount(String id) {
        Optional<BankAccount> found = findById(Long.valueOf(id));
        if (found.isPresent()){
            BankAccount bankAccount = found.get();
            List<Card> cards = bankAccount.getCards();
            deleteLinkedCards(cards);
            List<Transaction> incomingTransactions = bankAccount.getIncomingTransactions();
            List<Transaction> outgoingTransactions = bankAccount.getOutgoingTransactions();
            List<Transaction> transactions = new ArrayList<>();
            transactions.addAll(incomingTransactions);
            transactions.addAll(outgoingTransactions);
            deleteLinkedTransactions(transactions);
        }
        this.bankAccountRepository.deleteById(Long.valueOf(id));
    }

    public void deleteLinkedCards(List<Card> cards) {
        for (Card card : cards) {
            cardService.delete(card);
        }
    }

    public void deleteLinkedTransactions(List<Transaction> transactions) {
        for (Transaction transaction: transactions) {
            transactionService.delete(transaction);
        }
    }

    public Transaction sendMoney(Transaction transaction) {
        deductMoney(transaction);
        addMoney(transaction);
        transaction.setStatus("approved");
        return this.transactionService.save(transaction);
    }

    public void requestMoney(Transaction transaction) {
        Transaction createdTransaction = transactionService.save(transaction);
    }

    @Transactional
    public Optional<Transaction> approveRequestedMoney(String id) {
        Optional<Transaction> found = this.transactionService.findById(Long.valueOf(id));
        if (found.isPresent()) {
            Transaction transaction = found.get();
            sendMoney(transaction);
        }
        return found;
    }

    public void declineRequestedMoney(String id) {
        Optional<Transaction> found = this.transactionService.findById(Long.valueOf(id));
        if (found.isPresent()) {
            Transaction transaction = found.get();
            this.transactionService.delete(transaction);
        }
    }
}
