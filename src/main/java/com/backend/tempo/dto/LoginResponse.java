package com.backend.tempo.dto;

import lombok.Data;
import com.backend.tempo.database.User;

@Data
public class LoginResponse {
    private Integer userId;
    private String email;
    private String token;

    public LoginResponse(User user, String token) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.token = token;
    }
}