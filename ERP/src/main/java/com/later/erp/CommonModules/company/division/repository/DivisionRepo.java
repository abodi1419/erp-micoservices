package com.later.erp.CommonModules.company.division.repository;

import com.later.erp.CommonModules.company.division.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionRepo extends JpaRepository<Division, Long> {
    List<Division> findAllByDepartment_Id(Long departmentId);
}
