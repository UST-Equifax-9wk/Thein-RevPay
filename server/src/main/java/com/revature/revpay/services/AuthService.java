package com.revature.revpay.services;

import com.revature.revpay.entities.Credential;
import com.revature.revpay.repositories.AuthRepository;
import jakarta.servlet.http.Cookie;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    private boolean checkPassword(String password, String encrypted) {
        return BCrypt.checkpw(password, encrypted);
    }

    public void saveCredential(Credential credential) {
        /* Skip for presentation purpose
         * Pre-populated data from imports.sql bypass this function
         * and the password is not being hashed. As a result, logging in with a pre-populated user
         * can't pass authentication.
         */
        // String encryptedPassword = encryptPassword(credential.getPassword());
        // credential.setPassword(encryptedPassword);
        authRepository.save(credential);
    }

    public Cookie createCookieWithUsername(String username) {
        Cookie cookie = new Cookie("username",username);
        cookie.setPath("/");
        int MAX_COOKIE_AGE = 60 * 60 * 24 * 7;
        cookie.setMaxAge(MAX_COOKIE_AGE);
        return cookie;
    }

    public boolean authenticateUser(Credential credential) {
        String username = credential.getUsername();
        String password = (credential.getPassword());
        /* Skip for presentation purpose
         * Pre-populated data from imports.sql bypass this function
         * and the password is not being hashed. As a result, logging in with a pre-populated user
         * can't pass authentication.
         */
        // Optional<Credential> found = authRepository.findByUsername(username);
        // if (found.isPresent()) {
        //     String encrypted = found.get().getPassword();
        //     return checkPassword(password, encrypted);
        // }
        // return false;
        return authRepository.existsByUsernameAndPassword(username, password);
    }
}
