package com.backend.tempo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.tempo.repositories.UserRepository;
import com.backend.tempo.database.User;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        log.info("Registering new user: {}", user.getEmail());
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            log.info("User with email {} already exists", user.getEmail());
            return null;
        }
        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        log.info("Authenticating user: {}", email);
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}