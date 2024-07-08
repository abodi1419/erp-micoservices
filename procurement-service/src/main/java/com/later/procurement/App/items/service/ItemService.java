package com.later.procurement.App.items.service;


import com.later.procurement.App.items.entity.Item;
import com.later.procurement.Exception.ApiException;
import com.later.procurement.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

@Service
@RequiredArgsConstructor
public class ItemService {


    private final RestTemplate restTemplate;

    public List<Item> findAll() {
        ResponseEntity<ApiResponse<List<Item>>> response = restTemplate.exchange(
                "http://item-service/api/v1/ms/items/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Item>>>() {
                }
        );
        return response.getBody().getData();
    }

    public List<Item> findAllById(List<Long> ids) {
        HttpEntity<?> httpEntity = new HttpEntity<Object>(ids);
        ResponseEntity<ApiResponse<List<Item>>> response = restTemplate.exchange(
                "http://item-service/api/v1/ms/items/list",
                POST,
                httpEntity,
                new ParameterizedTypeReference<ApiResponse<List<Item>>>() {
                }
        );
        return response.getBody().getData();
    }

    public List<Item> findAllByIdUnderDiscipline(List<Long> ids, Long disciplineId) {
        HttpEntity<?> httpEntity = new HttpEntity<Object>(ids);
        ResponseEntity<ApiResponse<List<Item>>> response = restTemplate.exchange(
                "http://item-service/api/v1/ms/items/list?disciplineId=" + disciplineId,
                POST,
                httpEntity,
                new ParameterizedTypeReference<ApiResponse<List<Item>>>() {
                }
        );
        return response.getBody().getData();
    }

    public Item findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Item>> response = restTemplate.exchange(
                "http://item-service/api/v1/ms/items/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Item>>() {
                }
        );
        return response.getBody().getData();
    }

    public List<Item> findAllUnderDiscipline(Long disciplineId) {
        ResponseEntity<ApiResponse<List<Item>>> response = restTemplate.exchange(
                "http://item-service/api/v1/ms/items/list?disciplineId?id=" + disciplineId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Item>>>() {
                }
        );
        return response.getBody().getData();
    }
}
