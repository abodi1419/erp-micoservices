package com.later.authserver.Security.Auth.controllers;


import com.later.authserver.Security.Auth.entities.LoginUser;
import com.later.authserver.Security.Auth.models.LoginUserModel;
import com.later.authserver.Security.Auth.models.validation.LoginModel;
import com.later.authserver.Security.Auth.models.LoginResponse;
import com.later.authserver.Security.Auth.models.validation.LoginUserCreationModel;
import com.later.authserver.Security.Auth.models.LoginUserShortModel;
import com.later.authserver.Security.Auth.services.AuthService;
import com.later.authserver.Security.entities.LoginToken;
import com.later.authserver.Security.services.JwtService;
import com.later.authserver.Exception.ApiException;
import com.later.authserver.constants.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RequestMapping("api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody @Valid LoginUserCreationModel registerUserDto) throws ApiException {
        LoginUser registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(new ApiResponse(true, 200, "Success", "Registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody @Valid LoginModel loginUserDto) {
        LoginUser authenticatedUser = authenticationService.authenticate(loginUserDto);

        LoginToken jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken.getToken());
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setLoginUser(authenticationService.toModel(authenticatedUser));

        return ResponseEntity.ok(new ApiResponse(true, 200, "Success", authenticationService.toModel(authenticatedUser),
                jwtToken.getToken(), jwtToken.getExpiresAt().atZone(ZoneId.systemDefault()).toEpochSecond()));
    }

    @GetMapping("/check")
    public ResponseEntity authenticatedUser() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new ApiResponse<LoginUserShortModel>(true, 200, "Success", new LoginUserShortModel(loginUser), loginUser.getToken(),loginUser.getExpiresAt()));
    }

}

