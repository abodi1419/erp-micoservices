package com.later.procurement.App.items.service;


import com.later.procurement.App.items.entity.Item;
import com.later.procurement.CommonModules.addresses.entity.City;
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
public class ItemService {


    private final RestTemplate restTemplate;

    public List<Item> findAll() {
        ResponseEntity<ApiResponse<List<Item>>> response = restTemplate.exchange(
                "http://itemService/api/v1/item",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Item>>>() {}
        );
        return response.getBody().getData();
    }

    public List<Item> findAllById(List<Long> ids) {
        return List.of();
    }

    public List<Item> findAllByIdUnderDiscipline(List<Long> ids, Long disciplineId) {
        return List.of();
    }

    public Item findById(Long id) throws ApiException {
        return null;
    }

    public List<Item> findAllUnderDiscipline(Long disciplineId) {
        return List.of();
    }
}
