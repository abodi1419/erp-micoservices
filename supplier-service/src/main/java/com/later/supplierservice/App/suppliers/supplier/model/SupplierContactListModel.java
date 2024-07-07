package com.later.supplierservice.App.suppliers.supplier.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SupplierContactListModel {

    private Long id;
    private String contactName;
    private String contactNameAr;
    private String contactJobTitle;
    private String contactJobTitleAr;
    private String contactEmail;
    private String contactPhone;
    private String contactCity;
    private Short preferred;

    public SupplierContactListModel(Long id, String contactName, String contactNameAr, String contactJobTitle,
                                    String contactJobTitleAr, String contactEmail, String contactPhone, String contactCity,
                                    Short preferred) {
        this.id = id;
        this.contactName = contactName;
        this.contactNameAr = contactNameAr;
        this.contactJobTitle = contactJobTitle;
        this.contactJobTitleAr = contactJobTitleAr;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.contactCity = contactCity;
        this.preferred = preferred;
    }
}
