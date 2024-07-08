package com.later.itemservice.CommonModules.deliveryLocation.service;


import com.later.itemservice.CommonModules.deliveryLocation.entity.DeliveryLocation;
import com.later.itemservice.Exception.ApiException;
import com.later.itemservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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
                "http://common-service/api/v1/common/delivery-locations/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<DeliveryLocation>>>() {
                }
        );
        return response.getBody().getData();
    }

    public DeliveryLocation findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<DeliveryLocation>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/delivery-locations/list?id=" + id,
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
                "http://common-service/api/v1/common/delivery-locations/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<DeliveryLocation>>() {
                }
        );

        return response.getBody().getData();
    }

    public List<DeliveryLocation> findAllById(List<Long> locationsIds) {
        HttpEntity<?> httpEntity = new HttpEntity<Object>(locationsIds);
        ResponseEntity<ApiResponse<List<DeliveryLocation>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/delivery-locations/list",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<ApiResponse<List<DeliveryLocation>>>() {
                }
        );
        return response.getBody().getData();
    }
}
