package com.later.supplierservice.CommonModules.company.division.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.supplierservice.CommonModules.company.department.entity.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Division {
    private Long id;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private Department department;
}
