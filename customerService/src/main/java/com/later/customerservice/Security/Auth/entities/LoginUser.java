package com.later.customerservice.Security.Auth.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Setter
public class LoginUser implements UserDetails {
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    private Boolean resetFlag;

    private String resetPassword;

    private Employee employee;

    @Column(columnDefinition = "boolean default false")
    private Boolean admin;

    transient
    private List<Authority> authorities;

    @Transient
    private String token;
    @Column(columnDefinition = "bit default 0")
    private Boolean superAdmin = false;


}
