package com.later.supplierservice.CommonModules.deliveryLocation.service;


import com.later.supplierservice.CommonModules.deliveryLocation.entity.DeliveryLocation;
import com.later.supplierservice.Exception.ApiException;
import com.later.supplierservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryLocationService {

    private final RestTemplate restTemplate;

    public List<DeliveryLocation> findAll() {
        ResponseEntity<ApiResponse<List<DeliveryLocation>>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/delivery-locations/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<DeliveryLocation>>>() {
                }
        );
        return response.getBody().getData();
    }

    public DeliveryLocation findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<DeliveryLocation>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/delivery-locations/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<DeliveryLocation>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Vat Percentage not found");
        }
        return response.getBody().getData();
    }

    public DeliveryLocation findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<DeliveryLocation>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/delivery-locations/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<DeliveryLocation>>() {
                }
        );

        return response.getBody().getData();
    }
}
