package com.later.erp.App.procurement.PurchaseOrder.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseOrderCreationModel {
    private Long purchaseRequestId;
    // Purchase, Service
    @NotNull
    @Positive
    private Short type;
    @NotNull
    private Boolean revision;
    @NotNull
    private Boolean pettyCash;
    @NotNull
    @Positive
    private Long supplierId;
    @NotNull
    @Positive
    private Long supplierContact;
    @NotNull
    @Positive
    private Long currency;
    @NotNull
    @Positive
    private Double currencyRate;
    @NotNull
    @PositiveOrZero
    private Double advancePayment;
    @NotNull
    @Positive
    private Long shippingVatPercentage;
    @NotNull
    @PositiveOrZero
    private Double shippingCost;
    @NotNull
    @Positive
    private Long deliveryLocation;
    private Long revisedPurchaseOrder;
    private String deliveryTerms;
    private String paymentTerms;
    private String warrantyTerms;
    private List<String> attachments;


    @NotNull
    @NotEmpty
    @Valid
    private List<PurchaseOrderDetailsCreationModel> purchaseOrderDetailsCreationModels;


}
