package com.later.erp.Authorization.Security.Auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
