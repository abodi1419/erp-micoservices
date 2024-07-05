package com.later.itemservice.App.items.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "item_items_history")
public class ItemHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long refId;
    private Long itemCategory;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private String refCode;
    private Integer serial;
    private Double averagePrice;
    private Double highestPrice;
    private Double lowestPrice;
    private String image;
    private Long measureUnit;
    private String barcode;
    private Boolean product;
    private Long actionBy;
    private String action;
    private LocalDateTime actionAt = LocalDateTime.now();
    private Long discipline;

}
