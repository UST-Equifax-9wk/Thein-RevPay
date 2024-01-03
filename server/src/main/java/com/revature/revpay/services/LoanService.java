package com.revature.revpay.services;

import com.revature.revpay.entities.Admin;
import com.revature.revpay.entities.Loan;
import com.revature.revpay.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final AdminService adminService;

    @Autowired
    public LoanService(LoanRepository loanRepository, AdminService adminService) {
        this.loanRepository = loanRepository;
        this.adminService = adminService;
    }

    public Loan applyForLoan(Loan loan) {
        loan.setStatus("pending");
        Optional<Admin> found = adminService.findByUsername("th");
        if (found.isPresent()) {
            Admin admin = found.get();
            loan.setAdmin(admin);
        }
        return this.loanRepository.save(loan);
    }

    public void save(Loan loan) {
        loanRepository.save(loan);
    }
}
