package com.later.erpgateway.constants;


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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
