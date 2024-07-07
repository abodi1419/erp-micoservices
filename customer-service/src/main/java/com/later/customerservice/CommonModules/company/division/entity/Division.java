package com.later.customerservice.CommonModules.company.division.entity;

import com.later.customerservice.CommonModules.company.department.entity.Department;
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
