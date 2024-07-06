package com.later.erp.constants;

import com.later.erp.Authorization.Security.Auth.entities.LoginUser;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class ApiResponse {
    private boolean success;
    private int code;
    private String message = "";
    private String description = "";
    private Object data;
    private String accessToken;

    public ApiResponse(boolean success, int code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            accessToken = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken();
        }
    }

    public ApiResponse(boolean success, int code, String message, Object data, String accessToken) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.accessToken = accessToken;
    }

    public ApiResponse(boolean success, int code, String message, String description) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
