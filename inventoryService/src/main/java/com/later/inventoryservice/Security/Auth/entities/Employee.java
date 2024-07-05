package com.later.inventoryservice.Security.Auth.entities;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class Employee {
    private Long id;
    private Long employeeId;
    private String employeeFullName;
    private String employeeFullNameAr;
    private String employeeCompanyNumber;
    private String employeeShortName;
    private String employeeShortNameAr;
    private String employeeEmail;
    private LocalDate employeeHireDate;
    @Transient
    private LoginUser loginUser;

}
