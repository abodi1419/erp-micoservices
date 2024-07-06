package com.later.erp.App.inventory.warehouses.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "war_warehouses_history")
public class WarehouseHistory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    private String address;
    private String addressAr;
    private String refCode;
    private Long cityId;
    private String warehouseTypesId;
    private Long actionBy;
    private String action;
    private LocalDateTime actionAt = now();
}
