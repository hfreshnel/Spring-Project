package com.isn.quizplatform.model;

public class AuthResponse {
    private String token;
    private int role;

    // Constructeurs
    public AuthResponse() {}

    public AuthResponse(String token, int role) {
        this.token = token;
        this.role = role;
    }

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
