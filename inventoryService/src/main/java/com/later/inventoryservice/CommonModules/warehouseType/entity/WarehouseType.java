package com.later.inventoryservice.CommonModules.warehouseType.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

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
