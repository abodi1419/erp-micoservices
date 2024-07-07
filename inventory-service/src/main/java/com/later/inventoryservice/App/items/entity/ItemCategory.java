package com.later.inventoryservice.App.items.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class ItemCategory {
    private Long id;

    private String name;

    private String nameAr;
    private String description;
    private String descriptionAr;

    private ItemCategory parent;

    private List<ItemCategory> itemCategories;

    private List<Item> items;

}
