package com.later.companyservice.CommonModules.currencies.service;


import com.later.companyservice.CommonModules.banks.entity.Bank;
import com.later.companyservice.CommonModules.currencies.entity.Currency;
import com.later.companyservice.Exception.ApiException;
import com.later.companyservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    final private RestTemplate restTemplate;

    public List<Currency> findAll() {
        ResponseEntity<ApiResponse<List<Currency>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/currencies/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Currency>>>() {
                }
        );
        return response.getBody().getData();
    }

    public Currency findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Currency>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/currencies/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Currency>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "currencies not found");
        }
        return response.getBody().getData();
    }

    public Currency findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Currency>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/currencies/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Currency>>() {
                }
        );

        return response.getBody().getData();
    }
}
