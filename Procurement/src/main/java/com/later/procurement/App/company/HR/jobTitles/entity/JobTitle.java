package com.later.procurement.App.company.HR.jobTitles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.procurement.App.company.employees.entity.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class JobTitle {
    private Long id;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private List<Employee> employees;


}
