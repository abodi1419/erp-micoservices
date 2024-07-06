package com.later.erp.CommonModules.company.department.repository;

import com.later.erp.CommonModules.company.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    List<Department> findAllByCostCenter_Id(Long costCenterId);
}
