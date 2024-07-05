package com.later.inventoryservice.config;

import com.later.inventoryservice.Security.Auth.entities.LoginUser;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class AuthInterceptor implements ClientHttpRequestInterceptor {


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Add the Authorization header
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof LoginUser) {
            request.getHeaders().add("Authorization", "Bearer " +
                    ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken());
        }
        return execution.execute(request, body);
    }
}