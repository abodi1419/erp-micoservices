package com.later.authserver.Security.Auth.models.validation;

import jakarta.validation.constraints.NotNull;

public class LoginUserCreationModel {
    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Password is required")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
