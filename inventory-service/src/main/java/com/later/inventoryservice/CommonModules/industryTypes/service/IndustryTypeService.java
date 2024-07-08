package com.later.inventoryservice.CommonModules.industryTypes.service;


import com.later.inventoryservice.CommonModules.industryTypes.entity.IndustryType;
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
public class IndustryTypeService {
    final RestTemplate restTemplate;

    public List<IndustryType> findAll() {
        ResponseEntity<ApiResponse<List<IndustryType>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/industry-types/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<IndustryType>>>() {
                }
        );
        return response.getBody().getData();
    }

    public IndustryType findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<IndustryType>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/industry-types/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<IndustryType>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Industry type not found");
        }
        return response.getBody().getData();
    }

    public IndustryType findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<IndustryType>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/industry-types/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<IndustryType>>() {
                }
        );
        return response.getBody().getData();
    }
}
