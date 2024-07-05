package com.later.itemservice.App.items.entity;


import com.later.itemservice.interfaces.commonEntity.DisciplineInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "item_items")
public class Item implements DisciplineInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ItemCategory itemCategory;
    @Column(unique = true)
    private String name;
    private Long disciplineId;
    private String disciplineName;
    private String disciplineNameAr;
    private String disciplineCode;
    @Column(unique = true)
    private String nameAr;
    private String description;
    private String descriptionAr;
    @Column(unique = true)
    private String refCode;
    @Column(unique = true)
    private Integer serial;
    private Double averagePrice;
    private Double highestPrice;
    private Double lowestPrice;
    private String image;
    @ManyToOne
    private MeasureUnit measureUnit;
    private String barcode;
    private Boolean service;
    private Integer reorderLevel;
    private Integer reorderQuantity;
}
