package com.later.inventoryservice.CommonModules.vat.service;


import com.later.inventoryservice.CommonModules.vat.entity.VatPercentage;
import com.later.inventoryservice.Exception.ApiException;
import com.later.inventoryservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VatPercentageService {

    private final RestTemplate restTemplate;

    public List<VatPercentage> findAll() {
        ResponseEntity<ApiResponse<List<VatPercentage>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/vat-percentages/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<VatPercentage>>>() {
                }
        );
        return response.getBody().getData();
    }

    public VatPercentage findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<VatPercentage>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/vat-percentages/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<VatPercentage>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Vat Percentage not found");
        }
        return response.getBody().getData();
    }

    public VatPercentage findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<VatPercentage>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/vat-percentages/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<VatPercentage>>() {
                }
        );

        return response.getBody().getData();
    }


}
