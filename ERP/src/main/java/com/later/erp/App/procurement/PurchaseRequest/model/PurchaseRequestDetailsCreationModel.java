package com.later.erp.App.procurement.PurchaseRequest.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseRequestDetailsCreationModel {
    @NotNull
    @Positive
    private Long item;
    private String descriptionOfGoods;
    private String descriptionOfGoodsAr;
    @NotNull
    @Positive
    private Double quantity;
    @NotNull
    @Positive
    private Long deliveryLocation;
    private String remarks;
    @NotNull
    @PositiveOrZero
    private Double estUnitPrice;
}
