package com.later.companyservice.App.company.HR.jobTitles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.companyservice.App.company.employees.entity.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "hr_job_titles")

public class JobTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String nameAr;
    private String description;
    private String descriptionAr;
    @OneToMany(mappedBy = "jobTitle")
    @JsonIgnore
    private List<Employee> employees;


}
