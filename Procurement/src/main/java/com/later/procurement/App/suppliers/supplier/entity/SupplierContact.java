package com.later.procurement.App.suppliers.supplier.entity;

import com.later.procurement.interfaces.commonEntity.CityInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class SupplierContact implements CityInterface {
    private Long id;
    private Supplier supplier;
    private String contactName;
    private String contactNameAr;
    private String contactJobTitle;
    private String contactJobTitleAr;
    private String contactEmail;
    private String contactPhone;
    private Long cityId;
    private String cityName;
    private String cityNameAr;
    private Short preferred;
}
