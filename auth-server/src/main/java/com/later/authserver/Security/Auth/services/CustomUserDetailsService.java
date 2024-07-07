package com.later.authserver.Security.Auth.services;

import com.later.authserver.Exception.ApiException;
import com.later.authserver.Security.Auth.entities.LoginUser;
import com.later.authserver.Security.Auth.repositories.LoginUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final LoginUserRepo loginUserRepo;
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = loginUserRepo.getLoginUserByUsername(username);
        if (loginUser == null) {
            throw new ApiException(400,"Bad credentials");
        }
        return loginUser;
    }
}
