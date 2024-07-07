package com.later.commonservice.CommonModules.company.department.entity;

import com.later.commonservice.CommonModules.company.costCenter.entity.CostCenter;
import com.later.commonservice.CommonModules.company.division.entity.Division;
import com.later.commonservice.interfaces.commonEntity.EmployeeInterfaces.ManagerInterface;
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
public class Department implements ManagerInterface {
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
    private Long managerId;
    private String managerName;
    private String managerNameAr;
    private String managerCompanyNumber;
    private String location;
    @OneToMany(mappedBy = "department")
    private List<Division> divisions;

}
