package com.later.inventoryservice.App.inventory.warehouses.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDetailsCreationModel {
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nameAr;
    private String description;
    private String descriptionAr;
    private Long warehouseId;
}
