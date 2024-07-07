package com.later.procurement.App.suppliers.cashSupplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.later.procurement.interfaces.commonEntity.CityInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class CashSupplierContact implements CityInterface {

    private Long id;

    private CashSupplier supplier;
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
