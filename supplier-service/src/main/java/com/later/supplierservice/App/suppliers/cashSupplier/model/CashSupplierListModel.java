package com.later.supplierservice.App.suppliers.cashSupplier.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CashSupplierListModel {

    private Long id;
    private String companyName;
    private String companyNameAr;
    private String preferredCurrency;
    private String vatNumber;
    private String address;
    private String refCode;
    private String createdAt;

    public CashSupplierListModel(Long id, String companyName, String companyNameAr, String preferredCurrency,
                                 String vatNumber, String address, String refCode, LocalDateTime createdAt) {
        this.id = id;
        this.companyName = companyName;
        this.companyNameAr = companyNameAr;
        this.preferredCurrency = preferredCurrency;
        this.vatNumber = vatNumber;
        this.address = address;
        this.refCode = refCode;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
