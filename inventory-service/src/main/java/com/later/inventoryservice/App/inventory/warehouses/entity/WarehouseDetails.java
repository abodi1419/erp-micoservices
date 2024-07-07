package com.later.inventoryservice.App.inventory.warehouses.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "war_warehouse_details")
public class WarehouseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String descriptionAr;
    @ManyToOne
    @JsonIgnore
    private Warehouse warehouse;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
