package com.later.erp.CommonModules.company.costCenter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_cost_center")
public class CostCenter {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    private String code;
    private String description;
    private String descriptionAr;
    private Long parentId;
    @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
    private List<CostCenter> children;

}
