package com.later.procurement.App.procurement.PurchaseOrder.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderDetailsCreationModel {
    private String remarks;
    @NotNull
    @Positive
    private Double quantity;
    @NotNull
    @Positive
    private Long vatPercentage;
    @NotNull
    @Positive
    private Double unitPrice;
    @NotNull
    @Positive
    private Double unitDiscount;
    @NotNull
    @Positive
    private Long purchaseRequestDetails;
}
