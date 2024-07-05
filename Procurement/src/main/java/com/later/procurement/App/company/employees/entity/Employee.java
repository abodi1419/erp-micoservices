package com.later.procurement.App.company.employees.entity;


import com.later.procurement.App.company.HR.jobTitles.entity.JobTitle;
import com.later.procurement.Security.Auth.entities.LoginUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String firstNameAr;
    private String middleNameAr;
    private String lastNameAr;
    private String fullName;
    private String fullNameAr;
    private String shorName;
    private String shorNameAr;
    private String companyNumber;
    private Integer active;
    private String ssn;
    private LocalDate dob;
    private LocalDate dateOfHire;
    private Long loginUserId;
    private String imagePath;
    private String email;
    private JobTitle jobTitle;
    private Integer level;
    private LoginUser loginUser;
}
