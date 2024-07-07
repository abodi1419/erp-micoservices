package com.later.customerservice.CommonModules.warehouseType.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class WarehouseType {
    private Long id;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private Boolean allowSales;
}
