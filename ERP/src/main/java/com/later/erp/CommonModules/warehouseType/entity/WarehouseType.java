package com.later.erp.CommonModules.warehouseType.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_warehouse_types")
public class WarehouseType {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String descriptionAr;
    @Column(columnDefinition ="bit",nullable = false)
    private Boolean allowSales;
}
