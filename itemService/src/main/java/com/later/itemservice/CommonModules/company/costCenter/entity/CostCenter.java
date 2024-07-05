package com.later.itemservice.CommonModules.company.costCenter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

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
