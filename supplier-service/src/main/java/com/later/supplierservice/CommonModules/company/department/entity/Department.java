package com.later.supplierservice.CommonModules.company.department.entity;


import com.later.supplierservice.CommonModules.company.costCenter.entity.CostCenter;
import com.later.supplierservice.CommonModules.company.division.entity.Division;
import com.later.supplierservice.Security.Auth.entities.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class Department {
    private Long id;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private CostCenter costCenter;
    private String refCode;
    private Integer serial;
    private Employee manager;
    private String location;
    private List<Division> divisions;

}
