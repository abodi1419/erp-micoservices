package com.later.authserver.Security.Auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sec_employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String employeeFullName;
    private String employeeFullNameAr;
    private String employeeCompanyNumber;
    private String employeeShortName;
    private String employeeShortNameAr;
    private String employeeEmail;
    private LocalDate employeeHireDate;
    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private LoginUser loginUser;

}
