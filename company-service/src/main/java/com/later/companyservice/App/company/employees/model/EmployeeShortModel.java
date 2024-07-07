package com.later.companyservice.App.company.employees.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeShortModel {
    private Long id;
    private String fullName;
    private String fullNameAr;
    private String companyNumber;

    public EmployeeShortModel(Long id, String fullName, String fullNameAr, String companyNumber) {
        this.id = id;
        this.fullName = fullName;
        this.fullNameAr = fullNameAr;
        this.companyNumber = companyNumber;
    }
}
