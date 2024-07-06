package com.later.erp.CommonModules.company.division.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.erp.CommonModules.company.department.entity.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_divisions")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String descriptionAr;
    @ManyToOne
    @JsonIgnore
    private Department department;
}
