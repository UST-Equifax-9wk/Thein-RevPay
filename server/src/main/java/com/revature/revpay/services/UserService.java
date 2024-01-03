package com.revature.revpay.services;

import com.revature.revpay.entities.User;
import com.revature.revpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(Long.valueOf(id));
    }

    public List<User> findAllByUsernameOrEmail(String username, String email) {
        return userRepository.findAllByUsernameOrEmail(username, email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
