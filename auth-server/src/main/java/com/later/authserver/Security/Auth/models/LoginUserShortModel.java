package com.later.authserver.Security.Auth.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.later.authserver.Security.Auth.entities.LoginUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginUserShortModel {

    private Long id;
    private String username;
    private String email;
    private Boolean resetFlag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private Boolean isAdmin;
    private List<String> authorities;

    public LoginUserShortModel(LoginUser loginUser) {
        this.id = loginUser.getId();
        this.username = loginUser.getUsername();
        this.email = loginUser.getEmail();
        this.resetFlag = loginUser.getResetFlag();
        this.createdAt = loginUser.getCreatedAt();
        this.isAdmin = loginUser.getAdmin();
        this.authorities = new ArrayList<>();
        loginUser.getAuthorities().forEach(l -> {
            authorities.add(l.getRoute().getRoute() + "_" + l.getRouteAccess().getAccess());
        });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getResetFlag() {
        return resetFlag;
    }

    public void setResetFlag(Boolean resetFlag) {
        this.resetFlag = resetFlag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
