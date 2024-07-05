package com.later.procurement.App.suppliers.supplier.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupplierListModel {
    private Long id;
    private String companyName;
    private String companyNameAr;
    private String primaryIndustry;
    private String size;
    private String refCode;
    private Boolean local;
    private Short status;
    private String preferredCurrency;

    public SupplierListModel(Long id, String companyName, String companyNameAr, String primaryIndustry,
                             String size, String refCode, Boolean local, Short status, String preferredCurrency) {
        this.id = id;
        this.companyName = companyName;
        this.companyNameAr = companyNameAr;
        this.primaryIndustry = primaryIndustry;
        this.size = size;
        this.refCode = refCode;
        this.local = local;
        this.status = status;
        this.preferredCurrency = preferredCurrency;
    }
}
