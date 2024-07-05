package com.later.procurement.App.company.employees.service;


import com.later.procurement.App.company.employees.entity.Employee;
import com.later.procurement.App.company.employees.model.EmployeeShortModel;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    public List<Employee> findAll() {
        return List.of();
    }

    public Employee findById(Long id) throws ApiException {
        return null;
    }


    public List<EmployeeShortModel> listShort() {
        return List.of();
    }
}
