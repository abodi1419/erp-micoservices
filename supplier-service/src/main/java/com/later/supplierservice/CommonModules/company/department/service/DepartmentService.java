package com.later.supplierservice.CommonModules.company.department.service;


import com.later.supplierservice.CommonModules.company.costCenter.entity.CostCenter;
import com.later.supplierservice.CommonModules.company.department.entity.Department;
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
public class DepartmentService {

    private final RestTemplate restTemplate;

    public List<Department> findAll() {
        ResponseEntity<ApiResponse<List<Department>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/departments/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Department>>>() {
                }
        );
        ;
        return response.getBody().getData();
    }


    public List<Department> findAllByCostCenter(Long costCenterId) {
        ResponseEntity<ApiResponse<List<Department>>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/departments/list?costCenterId=" + costCenterId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Department>>>() {
                }
        );
        ;
        return response.getBody().getData();
    }

    public Department findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Department>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/departments/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Department>>() {
                }
        );
        ;
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Department not found");
        }
        return response.getBody().getData();
    }

    public Department findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Department>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/departments/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Department>>() {
                }
        );
        ;
        return response.getBody().getData();
    }

    public Department findByIdUnderCostCenter(Long id, Long costCenterId) throws ApiException {
        ResponseEntity<ApiResponse<Department>> response = restTemplate.exchange(
                "http://common-service/api/v1/common/departments/list?id=" + id + "&costCenterId=" + costCenterId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Department>>() {
                }
        );
        ;
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Department not found");
        }
        return response.getBody().getData();
    }

}
