package com.revature.revpay.repositories;

import com.revature.revpay.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
}