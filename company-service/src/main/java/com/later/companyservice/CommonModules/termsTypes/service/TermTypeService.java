package com.later.companyservice.CommonModules.termsTypes.service;


import com.later.companyservice.CommonModules.termsTypes.entity.TermType;
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
public class TermTypeService {

    final private RestTemplate restTemplate;

    public List<TermType> findAll() {
        ResponseEntity<ApiResponse<List<TermType>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/term-types/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<TermType>>>() {
                }
        );
        return response.getBody().getData();
    }

    public TermType findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<TermType>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/term-types/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<TermType>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Term type not found");
        }
        return response.getBody().getData();
    }

    public TermType findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<TermType>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/term-types/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<TermType>>() {
                }
        );

        return response.getBody().getData();
    }
}
