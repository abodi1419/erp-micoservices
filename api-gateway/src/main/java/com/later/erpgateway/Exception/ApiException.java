package com.later.erpgateway.Exception;


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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
