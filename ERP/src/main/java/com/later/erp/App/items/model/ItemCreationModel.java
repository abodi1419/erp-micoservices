package com.later.erp.App.items.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreationModel {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nameAr;
    private String description;
    private String descriptionAr;
    private String refCode;
    @NotNull
    @Positive
    private Long categoryId;
    private Double averagePrice;
    private Double highestPrice;
    private Double lowestPrice;
    private String image;
    @NotNull
    @Positive
    private Long measureUnit;
    private String barcode;
    @NotNull
    @Positive
    private Long discipline;
    @NotNull
    private Boolean service;
    @NotNull
    @PositiveOrZero
    private Integer reorderLevel;
    @NotNull
    @PositiveOrZero
    private Integer reorderQuantity;
}
