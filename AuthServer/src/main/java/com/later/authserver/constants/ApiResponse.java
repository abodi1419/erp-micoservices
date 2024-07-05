package com.later.authserver.constants;

import com.later.authserver.Security.Auth.entities.LoginUser;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String message = "";
    private String description = "";
    private T data;
    private String accessToken;
    private Long expiresAt;

    public ApiResponse(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
        SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof LoginUser) {
            accessToken = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken();
            expiresAt = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getExpiresAt();
        }
    }

    public ApiResponse(boolean success, int code, String message, T data, String accessToken, Long expiresAt) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
    }

    public ApiResponse(boolean success, int code, String message, String description) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
