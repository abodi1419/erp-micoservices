package com.later.erp.App.inventory.warehouses.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "war_warehouse_details_history")
public class WarehouseDetailsHistory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String descriptionAr;
    private Long warehouseId;
    private Long actionBy;
    private String action;
    private LocalDateTime actionAt = LocalDateTime.now();
}
