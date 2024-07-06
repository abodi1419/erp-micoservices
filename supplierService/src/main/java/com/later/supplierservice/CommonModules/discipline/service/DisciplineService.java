package com.later.supplierservice.CommonModules.discipline.service;


import com.later.supplierservice.CommonModules.banks.entity.Bank;
import com.later.supplierservice.CommonModules.discipline.entity.Discipline;
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
public class DisciplineService {
final private RestTemplate restTemplate;

    public List<Discipline> findAll() {
        ResponseEntity<ApiResponse<List<Discipline>>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/disciplines/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Discipline>>>() {}
        );
        return response.getBody().getData();    }

    public Discipline findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Discipline>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/disciplines/list?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Discipline>>() {}
        );
        if(response.getBody().getData()==null){
            throw new ApiException(404, "Disciplines not found");
        }
        return response.getBody().getData();
    }

    public Discipline findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Discipline>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/disciplines/list?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Discipline>>() {}
        );

        return response.getBody().getData();   }
}
