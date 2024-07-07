package com.later.authserver.Security.Auth.models;


import com.later.authserver.Security.Auth.entities.LoginUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private LoginUserModel loginUser;
    private String token;
    private long expiresIn;
}
