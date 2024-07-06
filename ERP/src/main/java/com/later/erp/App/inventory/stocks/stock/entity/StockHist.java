package com.later.erp.App.inventory.stocks.stock.entity;

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
@Table(name = "war_stock_hist")
public class StockHist {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long stockId;
    private Long itemId;
    private String itemCode;
    private String itemName;
    private String itemNameAr;
    private String itemDescription;
    private String itemDescriptionAr;
    private Long unitOfMeasureId;
    private Long warehouseId;
    private Long warehouseDetailId;
    private Integer quantity;
    @Column(unique = true)
    private String refCode;
    @Column(unique = true)
    private Integer serial;
    @Column(columnDefinition = "tinyint")
    private Short status;
    private Long actionBy;
    private String action;
    private LocalDateTime actionAt = LocalDateTime.now();
}
