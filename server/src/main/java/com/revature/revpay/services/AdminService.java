package com.revature.revpay.services;

import com.revature.revpay.entities.Admin;
import com.revature.revpay.entities.BankAccount;
import com.revature.revpay.entities.Credential;
import com.revature.revpay.entities.Loan;
import com.revature.revpay.repositories.AdminRepository;
import com.revature.revpay.repositories.LoanRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final LoanRepository loanRepository;
    private final BankAccountService bankAccountService;

    @Autowired
    public AdminService(AdminRepository adminRepository, LoanRepository loanRepository, BankAccountService bankAccountService) {
        this.adminRepository = adminRepository;
        this.loanRepository = loanRepository;
        this.bankAccountService = bankAccountService;
    }

    public boolean authenticate(Credential credential) {
        return existsByUsernameAndPassword(credential.getUsername(), credential.getPassword());
    }

    public Optional<Admin> signIn(Credential credential) {
        boolean isAuthenticated = authenticate(credential);
        if (isAuthenticated) {
            return findByUsername(credential.getUsername());
        } else {
            return Optional.empty();
        }
    }

    public Cookie createCookieWithUsername(String username) {
        Cookie c = new Cookie("username", username);
        c.setPath("/");
        int MAX_COOKIE_AGE = 60 * 60 * 24 * 7;
        c.setMaxAge(MAX_COOKIE_AGE);
        return c;
    }

    public boolean existsByUsernameAndPassword(String username, String password) {
        return adminRepository.existsByUsernameAndPassword(username, password);
    }

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public void approveLoan(Loan loan) {
        Optional<Loan> found = loanRepository.findById(loan.getId());
        if (found.isPresent()) {
            Loan existed = found.get();
            existed.setStatus("approved");
            loanRepository.save(existed);
            updateBankAccount(existed.getAccount(), existed.getAmount());
        }
    }

    public void updateBankAccount(BankAccount bankAccount, Double amount) {
        Optional<BankAccount> foundAccount = bankAccountService.findById(bankAccount.getId());
        if (foundAccount.isPresent()) {
            BankAccount account = foundAccount.get();
            Double existingBalance = account.getBalance();
            account.setBalance(existingBalance + amount);
            bankAccountService.save(account);
        }
    }
}
