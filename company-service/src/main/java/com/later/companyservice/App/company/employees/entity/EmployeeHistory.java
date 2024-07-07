package com.later.companyservice.App.company.employees.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "hr_employees_history")

public class EmployeeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long refId;
    private String name;
    private String companyNumber;
    private Integer active;
    private String ssn;
    private LocalDate dob;
    private LocalDate dateOfHire;
    private Long loginUserId;
    private String imagePath;
    private String email;
    private Long jobTitle;
    private Integer level;
    private Long actionBy;
    private String action;
    private LocalDateTime actionAt = LocalDateTime.now();
}
