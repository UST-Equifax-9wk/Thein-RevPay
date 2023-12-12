package com.revature.RevPay.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Customer {
    private Integer id;
    private String username;
    private String password;
    private Collection<BankAccount> bankAccounts;

    public Customer() {
    }

    public Customer(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "accountHolder")
    public Collection<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Collection<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
