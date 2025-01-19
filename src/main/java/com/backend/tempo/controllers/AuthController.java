package com.backend.tempo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.backend.tempo.services.AuthService;
import com.backend.tempo.database.User;
import com.backend.tempo.dto.LoginResponse;
import com.backend.tempo.utils.JwtUtils;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody User user) {
        log.info("Registering new user with email: {}", user.getEmail());
        User registeredUser = authService.register(user);
        if (registeredUser != null) {
            String token = jwtUtil.generateToken(registeredUser.getId());
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new LoginResponse(registeredUser, token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody User user) {
        log.info("Login attempt for user: {}", user.getEmail());
        User authenticatedUser = authService.authenticate(user.getEmail(), user.getPassword());
        if (authenticatedUser != null) {
            String token = jwtUtil.generateToken(authenticatedUser.getId());
            return ResponseEntity.ok(new LoginResponse(authenticatedUser, token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}