package com.later.authserver.Security.Auth.interaces;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginUserInterface {
    UserDetailsService userDetailsService();
}
