package com.later.procurement.App.items.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


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
