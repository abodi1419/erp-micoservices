package com.later.itemservice.Exception;

import lombok.Data;

@Data
public class ApiException extends Exception {
    private int code;
    private String message;
    private String description;

    public ApiException(int code, String description) {
        super(description);
        this.code = code;
        this.message = "Error";
        this.description = description;
    }
}