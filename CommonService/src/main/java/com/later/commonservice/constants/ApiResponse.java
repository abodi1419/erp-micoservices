package com.later.commonservice.constants;

import com.later.commonservice.Security.Auth.entities.LoginUser;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {
    private boolean success;
    private int code;
    private String message = "";
    private String description = "";
    private T data;
    private String accessToken;

    public ApiResponse(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            accessToken = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken();
        }
    }

    public ApiResponse(boolean success, int code, String message, T data, String accessToken) {
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
