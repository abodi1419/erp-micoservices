package com.later.erp.CommonModules.company.department.entity;

import com.later.erp.App.company.employees.entity.Employee;
import com.later.erp.CommonModules.company.costCenter.entity.CostCenter;
import com.later.erp.CommonModules.company.division.entity.Division;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    @ManyToOne
    private CostCenter costCenter;
    private String refCode;
    private Integer serial;
    @ManyToOne
    private Employee manager;
    private String location;
    @OneToMany(mappedBy = "department")
    private List<Division> divisions;

}
