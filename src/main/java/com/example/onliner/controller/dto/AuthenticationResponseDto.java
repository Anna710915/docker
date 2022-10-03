package com.example.onliner.controller.dto;

import com.example.onliner.domain.User;

public class AuthenticationResponseDto {

    private String username;
    private String accessToken;
    private User.UserRole role;

    public AuthenticationResponseDto(String username, String accessToken, User.UserRole role) {
        this.username = username;
        this.accessToken = accessToken;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User.UserRole getRole() {
        return role;
    }

    public void setRole(User.UserRole role) {
        this.role = role;
    }
}
