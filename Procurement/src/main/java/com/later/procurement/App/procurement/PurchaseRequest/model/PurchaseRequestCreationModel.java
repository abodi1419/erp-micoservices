package com.later.procurement.App.procurement.PurchaseRequest.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PurchaseRequestCreationModel {
    @NotNull
    @Positive
    private Long costCenter;
    @NotNull
    @Positive
    private Long department;
    @NotNull
    @Positive
    private Long division;
    @NotNull
    @Positive
    private Long discipline;
    @NotNull
    private Boolean permanentItems;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;
    private String attachment;
    private String generalRemarks;
    private String deliveryTerms;
    @NotNull
    private Boolean service;
    @Valid
    @NotNull
    @NotEmpty
    private List<PurchaseRequestDetailsCreationModel> purchaseRequestDetailsCreationModelList;
    private Long buyer;
}
