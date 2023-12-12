package com.revature.RevPay.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class BankAccount {
    private Integer id;
    private Integer accountNumber;
    private Customer accountHolder;
    private Collection<Transaction> transactions;

    public BankAccount() {
    }

    public BankAccount(Integer id, Integer accountNumber, Customer accountHolder) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "account_number")
    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @ManyToOne
    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }

    @OneToMany(mappedBy = "bankAccount")
    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = transactions;
    }
}
