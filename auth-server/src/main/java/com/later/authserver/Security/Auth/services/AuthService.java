package com.later.authserver.Security.Auth.services;


import com.later.authserver.Exception.ApiException;
import com.later.authserver.Security.Auth.entities.Authority;
import com.later.authserver.Security.Auth.entities.LoginUser;
import com.later.authserver.Security.Auth.models.AuthorityModel;
import com.later.authserver.Security.Auth.models.LoginUserModel;
import com.later.authserver.Security.Auth.models.validation.LoginModel;
import com.later.authserver.Security.Auth.models.validation.LoginUserCreationModel;
import com.later.authserver.Security.Auth.repositories.LoginUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final LoginUserRepo loginUserRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public LoginUser signup(LoginUserCreationModel input) throws ApiException {
        LoginUser loginUser = loginUserRepo.findByEmailOrUsername(input.getEmail()).orElse(null);
        if (loginUser != null) {
            throw new ApiException(400, "You already have account!");
        }
        loginUser = loginUserRepo.getLoginUserByUsername(input.getUsername());
        if (loginUser != null) {
            throw new ApiException(400, "Username already used!");

        }
        LoginUser user = new LoginUser();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setResetFlag(false);
        user.setEnabled(true);
        user.setIsNonExpired(true);
        user.setIsNonLocked(true);

        return loginUserRepo.save(user);
    }

    public LoginUser authenticate(LoginModel input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        LoginUser loginUser = loginUserRepo.findByEmailOrUsername(input.getUsername())
                .orElse(null);
        return loginUser;
    }

    public LoginUserModel toModel(LoginUser loginUser) {
        LoginUserModel loginUserModel = new LoginUserModel();
        loginUserModel.setId(loginUser.getId());
        loginUserModel.setUsername(loginUser.getUsername());
        loginUserModel.setEmail(loginUser.getEmail());
        loginUserModel.setAdmin(loginUser.getAdmin());
        loginUserModel.setSuperAdmin(loginUser.getAdmin());
        loginUserModel.setIsNonExpired(loginUser.getIsNonExpired());
        loginUserModel.setIsNonLocked(loginUser.getIsNonLocked());
        loginUserModel.setResetFlag(loginUser.getResetFlag());
        HashMap<String, AuthorityModel> map = new HashMap<>();
        loginUserModel.setEmployee(loginUser.getEmployee());
        for (Authority authority : loginUser.getAuthorities()) {
            if (map.containsKey(authority.getRoute().getRoute())) {
                List<String> list = new ArrayList<>(map.get(authority.getRoute().getRoute()).getAuthorities());
                list.add(authority.getAuthority());
                map.get(authority.getRoute().getRoute()).setAuthorities(list);
            } else {
                AuthorityModel authorityModel = new AuthorityModel();
                authorityModel.setAdmin(authority.getRoute().getAdmin());
                authorityModel.setActive(authority.getRoute().getActive());
                authorityModel.setRoute(authority.getRoute().getRoute());
                authorityModel.setPublicAccess(authority.getRoute().getPublicAccess());
                authorityModel.setModuleName(authority.getRoute().getModuleName());
                authorityModel.setSuperAdmin(authority.getRoute().getSuperAdmin());
                authorityModel.setAuthorities(List.of(authority.getAuthority()));
                map.put(authority.getRoute().getRoute(), authorityModel);
            }
        }
        loginUserModel.setAuthorities(map.values().stream().toList());
        return loginUserModel;
    }


}
