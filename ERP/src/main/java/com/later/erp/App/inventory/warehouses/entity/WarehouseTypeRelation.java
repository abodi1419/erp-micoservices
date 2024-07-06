package com.later.erp.App.inventory.warehouses.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.erp.interfaces.commonEntity.WarehouseTypeInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "war_warehouse_types")
public class WarehouseTypeRelation implements WarehouseTypeInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long warehouseTypeId;
    private String warehouseTypeName;
    private String warehouseTypeNameAr;
    private Boolean allowSales;
    @ManyToOne
    @JsonIgnore
    private Warehouse warehouse;
}
