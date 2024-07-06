package com.later.erp.App.inventory.warehouses.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WarehouseCreationModel {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nameAr;
    private String address;
    private String addressAr;
    @NotNull
    @Positive
    private Long cityId;
    @NotNull
    @NotNull(message = "type is empty")
    private List<Long> warehouseTypeIds;
    private Long costCenterId;

}
