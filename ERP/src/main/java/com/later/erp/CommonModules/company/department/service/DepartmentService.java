package com.later.erp.CommonModules.company.department.service;

import com.later.erp.CommonModules.company.department.entity.Department;
import com.later.erp.CommonModules.company.department.repository.DepartmentRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    public List<Department> findAll() {
        return departmentRepo.findAll();
    }


    public List<Department> findAllByCostCenter(Long costCenterId) {
        return departmentRepo.findAllByCostCenter_Id(costCenterId);
    }

    public Department findById(Long id) throws ApiException {
        Department department = departmentRepo.findById(id).orElse(null);
        if (department == null) {
            throw new ApiException(404, "Department not found");
        }
        return department;
    }

    public Department findByIdUnderCostCenter(Long id, Long costCenterId) throws ApiException {
        Department department = departmentRepo.findById(id).orElse(null);
        if (department == null || department.getCostCenter() == null ||
                !department.getCostCenter().getId().equals(costCenterId)) {
            throw new ApiException(404, "Department not found");
        }
        return department;
    }

}
