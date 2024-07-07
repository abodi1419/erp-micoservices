package com.later.inventoryservice.App.inventory.inventoryTransactions.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "inv_transactions")
public class InventoryTransaction {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String systemCode;
    private String refCode;
    private Long itemId;
    private Long warehouseId;
    private Long warehouseDetails;
    private Double quantity;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;

    public InventoryTransaction(String systemCode, String refCode, Long itemId, Long warehouseId, Long warehouseDetails, Double quantity) {
        this.systemCode = systemCode;
        this.refCode = refCode;
        this.itemId = itemId;
        this.warehouseId = warehouseId;
        this.warehouseDetails = warehouseDetails;
        this.quantity = quantity;
    }
}
