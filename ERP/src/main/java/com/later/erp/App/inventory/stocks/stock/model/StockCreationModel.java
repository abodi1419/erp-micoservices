package com.later.erp.App.inventory.stocks.stock.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCreationModel {
    private Long id;
    @NotNull(message = "must select a item")
    private Long itemId;
    @NotNull(message = "must select a warehouse")
    private Long warehouseId;
    @NotNull(message = "must select a warehouseDetails")
    private Long warehouseDetailsId;
    private Integer quantity;
}
