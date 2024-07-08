package com.later.customerservice.CommonModules.banks.service;


import com.later.customerservice.CommonModules.banks.entity.Bank;
import com.later.customerservice.Exception.ApiException;
import com.later.customerservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final RestTemplate restTemplate;

    public List<Bank> findAll() {
        ResponseEntity<ApiResponse<List<Bank>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/banks/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Bank>>>() {
                }
        );
        return response.getBody().getData();
    }

    public Bank findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Bank>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/banks/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Bank>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Bank not found");
        }
        return response.getBody().getData();
    }

    public Bank findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Bank>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/banks/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Bank>>() {
                }
        );
        return response.getBody().getData();
    }
}
