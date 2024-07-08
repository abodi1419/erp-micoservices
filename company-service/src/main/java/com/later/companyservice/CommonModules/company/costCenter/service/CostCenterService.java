package com.later.companyservice.CommonModules.company.costCenter.service;


import com.later.companyservice.CommonModules.addresses.entity.Country;
import com.later.companyservice.CommonModules.company.costCenter.entity.CostCenter;
import com.later.companyservice.Exception.ApiException;
import com.later.companyservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostCenterService {

    private final RestTemplate restTemplate;

    public List<CostCenter> findAll() {
        ResponseEntity<ApiResponse<List<CostCenter>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/cost-centers/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<CostCenter>>>() {
                }
        );
        ;
        return response.getBody().getData();
    }

    public CostCenter findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<CostCenter>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/cost-centers/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<CostCenter>>() {
                }
        );
        ;
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Cost center not found");
        }
        return response.getBody().getData();
    }

    public CostCenter findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<CostCenter>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/cost-centers/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<CostCenter>>() {
                }
        );
        ;
        return response.getBody().getData();
    }
}
