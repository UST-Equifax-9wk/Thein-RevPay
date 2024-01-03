package com.revature.revpay.repositories;

import com.revature.revpay.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Credential, Long> {
    boolean existsByUsernameAndPassword(String username, String password);

    Optional<Credential> findByUsername(String username);
}
