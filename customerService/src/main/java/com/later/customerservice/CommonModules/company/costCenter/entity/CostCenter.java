package com.later.customerservice.CommonModules.company.costCenter.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class CostCenter {

    private Long id;
    private String name;
    private String nameAr;
    private String code;
    private String description;
    private String descriptionAr;
    private Long parentId;
    private List<CostCenter> children;

}
