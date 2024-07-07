package com.later.companyservice.App.company.employees.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.companyservice.App.company.HR.jobTitles.entity.JobTitle;
import com.later.companyservice.Security.Auth.entities.LoginUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "hr_employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    private JobTitle jobTitle;
    private Integer level;
    @Transient
    private LoginUser loginUser;
}
