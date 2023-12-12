package com.revature.RevPay.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

enum TransactionType {
    DEPOSIT, WITHDRAW, TRANSFER
}

@Entity
public class Transaction {
    private Integer id;
    private Double amount;
    private TransactionType transactionType;
    private BankAccount bankAccount;
    private Timestamp timestamp;

    public Transaction() {
    }

    public Transaction(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transaction_type")
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Column
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @ManyToOne
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
