package com.later.procurement.CommonModules.company.department.service;


import com.later.procurement.CommonModules.company.department.entity.Department;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    public List<Department> findAll() {
        return List.of();
    }


    public List<Department> findAllByCostCenter(Long costCenterId) {
        return List.of();
    }

    public Department findById(Long id) throws ApiException {
        return null;
    }

    public Department findByIdUnderCostCenter(Long id, Long costCenterId) throws ApiException {
        return null;
    }

}
