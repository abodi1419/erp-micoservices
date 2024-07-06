package com.later.supplierservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.later.supplierservice.Exception.ApiException;
import com.later.supplierservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class RestErrorHandler implements ResponseErrorHandler {
    private final ObjectMapper objectMapper;
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().equals(HttpStatusCode.valueOf(200));
    }

    @Override
    @SneakyThrows
    public void handleError(ClientHttpResponse response) throws IOException {
        ApiResponse apiResponse = objectMapper.readValue(new String(response.getBody().readAllBytes()), ApiResponse.class);
        throw new ApiException(apiResponse.getCode(), apiResponse.getDescription());

    }


}
