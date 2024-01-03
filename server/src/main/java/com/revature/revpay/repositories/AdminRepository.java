package com.revature.revpay.repositories;

import com.revature.revpay.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);
}
