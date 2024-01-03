package com.revature.revpay.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity(name = "transactions")
public class Transaction {
    private Long id;
    private Double amount;
    private Instant createdDate;
    private String status;
    private String description;

    private BankAccount senderAccount;
    private BankAccount receiverAccount;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Column
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "senderAccountId")
    @JsonBackReference(value = "senderAccount-outgoingTransaction")
    public BankAccount getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(BankAccount senderAccount) {
        this.senderAccount = senderAccount;
    }

    @ManyToOne
    @JoinColumn(name = "receiverAccountId")
    @JsonBackReference(value = "receiverAccount-incomingTransaction")
    public BankAccount getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(BankAccount receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdDate=" + createdDate +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", senderAccount=" + senderAccount +
                ", receiverAccount=" + receiverAccount +
                '}';
    }
}
