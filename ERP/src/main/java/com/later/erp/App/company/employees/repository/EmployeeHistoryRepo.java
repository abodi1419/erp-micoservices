package com.later.erp.App.company.employees.repository;

import com.later.erp.App.company.employees.entity.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeHistoryRepo extends JpaRepository<EmployeeHistory, Long> {
}
