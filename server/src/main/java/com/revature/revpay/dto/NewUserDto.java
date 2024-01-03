package com.revature.revpay.dto;

import com.revature.revpay.entities.Credential;
import com.revature.revpay.entities.User;

public class NewUserDto {
    private Credential credential;
    private User user;

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "NewUserDto{" +
                "credential=" + credential +
                ", user=" + user +
                '}';
    }
}
