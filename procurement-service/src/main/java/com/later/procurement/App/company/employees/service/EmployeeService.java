package com.later.procurement.App.company.employees.service;


import com.later.procurement.App.company.employees.entity.Employee;
import com.later.procurement.App.company.employees.model.EmployeeShortModel;
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
public class EmployeeService {

    private final RestTemplate restTemplate;

    public List<Employee> findAll() {
        ResponseEntity<ApiResponse<List<Employee>>> response = restTemplate.exchange(
                "http://company-service/api/v1/ms/employees/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Employee>>>() {
                }
        );
        return response.getBody().getData();
    }

    public Employee findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Employee>> response = restTemplate.exchange(
                "http://company-service/api/v1/ms/employees/list?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Employee>>() {
                }
        );
        if (response.getBody().getData() == null) {
            throw new ApiException(404, "Employee Not Found");
        }
        return response.getBody().getData();
    }


    public List<EmployeeShortModel> listShort() {
        ResponseEntity<ApiResponse<List<EmployeeShortModel>>> response = restTemplate.exchange(
                "http://company-service/api/v1/ms/employees/list?shortModel=true",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<EmployeeShortModel>>>() {
                }
        );
        return response.getBody().getData();
    }
}
