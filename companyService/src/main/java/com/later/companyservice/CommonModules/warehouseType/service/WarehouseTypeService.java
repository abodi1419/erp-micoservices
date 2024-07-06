package com.later.companyservice.CommonModules.warehouseType.service;


import com.later.companyservice.CommonModules.warehouseType.entity.WarehouseType;
import com.later.companyservice.Exception.ApiException;
import com.later.companyservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseTypeService {
    private final RestTemplate restTemplate;

    public List<WarehouseType> findAll() {
        ResponseEntity<ApiResponse<List<WarehouseType>>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/warehouse-types/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<WarehouseType>>>() {
                }
        );
        return response.getBody().getData();
    }

    public WarehouseType findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<WarehouseType>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/warehouse-types/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<WarehouseType>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Warehouse Type not found");
        }
        return response.getBody().getData();
    }

    public WarehouseType findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<WarehouseType>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/warehouse-types/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<WarehouseType>>() {
                }
        );
        return response.getBody().getData();
    }

    public List<WarehouseType> findAllById(List<Long> warehouseTypeIds) {
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        HttpEntity<?> httpEntity = new HttpEntity<Object>(warehouseTypeIds);
        ResponseEntity<ApiResponse<List<WarehouseType>>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/warehouse-types/list",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<ApiResponse<List<WarehouseType>>>() {
                }
        );
        return response.getBody().getData();
    }
}
