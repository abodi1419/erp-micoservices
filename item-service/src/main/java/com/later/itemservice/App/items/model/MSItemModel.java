package com.later.itemservice.App.items.model;


import com.later.itemservice.App.items.entity.ItemCategory;
import com.later.itemservice.App.items.entity.MeasureUnit;
import com.later.itemservice.interfaces.commonEntity.DisciplineInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class MSItemModel implements DisciplineInterface {
    private Long id;
    private ItemCategory itemCategory;
    private String name;
    private Long disciplineId;
    private String disciplineName;
    private String disciplineNameAr;
    private String disciplineCode;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private String refCode;
    private Integer serial;
    private Double averagePrice;
    private Double highestPrice;
    private Double lowestPrice;
    private String image;
    private MeasureUnit measureUnit;
    private String barcode;
    private Boolean service;
    private Integer reorderLevel;
    private Integer reorderQuantity;
}
