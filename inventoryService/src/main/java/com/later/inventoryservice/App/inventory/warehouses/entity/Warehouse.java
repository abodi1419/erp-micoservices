package com.later.inventoryservice.App.inventory.warehouses.entity;

import com.later.inventoryservice.interfaces.commonEntity.CityInterface;
import com.later.inventoryservice.interfaces.commonEntity.CostCenterInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "war_warehouses")
public class Warehouse implements CityInterface, CostCenterInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    private String address;
    private String addressAr;
    @Column(unique = true)
    private String refCode;
    @Column(unique = true)
    private Integer serial;
    private Long cityId;
    private String cityName;
    private String cityNameAr;
    @OneToMany(mappedBy = "warehouse")
    private List<WarehouseDetails> warehouseDetails;
    @OneToMany(mappedBy = "warehouse")
    private List<WarehouseTypeRelation> warehouseTypeRelations;
    private Long costCenterId;
    private String costCenterName;
    private String costCenterNameAr;
    private String costCenterRefCode;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
