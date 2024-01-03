package com.revature.revpay.entities;

import jakarta.persistence.*;

@Entity(name = "credentials")
@Table(indexes = @Index(columnList = "username"))
public class Credential {
    private String username;
    private String password;

    @Id
    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
