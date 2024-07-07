package com.later.companyservice.App.company.employees.repository;

import com.later.companyservice.App.company.employees.entity.Employee;
import com.later.companyservice.App.company.employees.model.EmployeeShortModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query("select new com.later.companyservice.App.company.employees.model.EmployeeShortModel(" +
            "e.id, e.fullName, e.fullNameAr, e.companyNumber ) " +
            " from Employee e where e.active=1 ")
    List<EmployeeShortModel> listShort();
}
