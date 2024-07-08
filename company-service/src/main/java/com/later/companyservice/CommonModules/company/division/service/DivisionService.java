package com.later.companyservice.CommonModules.company.division.service;

import com.later.companyservice.CommonModules.company.department.entity.Department;
import com.later.companyservice.CommonModules.company.division.entity.Division;
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
public class DivisionService {

    private final RestTemplate restTemplate;

    private List<Division> findAll() {
        ResponseEntity<ApiResponse<List<Division>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/divisions/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Division>>>() {
                }
        );
        ;
        return response.getBody().getData();
    }

    public List<Division> findAllUnderDepartment(Long departmentId) {
        ResponseEntity<ApiResponse<List<Division>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/divisions/list?departmentId=" + departmentId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Division>>>() {
                }
        );
        ;
        return response.getBody().getData();
    }

    public Division findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Division>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/divisions/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Division>>() {
                }
        );
        ;
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Division not found");
        }
        return response.getBody().getData();
    }

    public Division findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Division>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/divisions/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Division>>() {
                }
        );
        ;
        return response.getBody().getData();
    }

    public Division findByIdUnderDepartment(Long id, Long departmentId) throws ApiException {
        ResponseEntity<ApiResponse<Division>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/divisions/list?id=" + id + "&departmentId=" + departmentId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Division>>() {
                }
        );
        ;
        return response.getBody().getData();
    }

}
