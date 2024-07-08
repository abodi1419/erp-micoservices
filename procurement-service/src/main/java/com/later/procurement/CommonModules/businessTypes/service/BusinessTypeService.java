package com.later.procurement.CommonModules.businessTypes.service;


import com.later.procurement.CommonModules.businessTypes.entity.BusinessType;
import com.later.procurement.Exception.ApiException;
import com.later.procurement.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessTypeService {
    final private RestTemplate restTemplate;

    public List<BusinessType> findAll() {
        ResponseEntity<ApiResponse<List<BusinessType>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/business-types/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<BusinessType>>>() {
                }
        );
        return response.getBody().getData();
    }

    public BusinessType findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<BusinessType>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/business-types/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<BusinessType>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Bank not found");
        }
        return response.getBody().getData();
    }


    public BusinessType findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<BusinessType>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/business-types/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<BusinessType>>() {
                }
        );
        return response.getBody().getData();
    }

}
