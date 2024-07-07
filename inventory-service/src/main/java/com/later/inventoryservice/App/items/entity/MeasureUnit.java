package com.later.inventoryservice.App.items.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@RequiredArgsConstructor
@Getter
@Setter
public class MeasureUnit {
    private Long id;
    private String code;
    private String codeAr;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private Boolean service;
    private List<Item> items;

}
