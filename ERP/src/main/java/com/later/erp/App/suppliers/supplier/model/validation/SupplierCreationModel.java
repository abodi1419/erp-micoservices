package com.later.erp.App.suppliers.supplier.model.validation;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SupplierCreationModel {
    @NotNull
    @NotBlank
    private String companyName;
    private String companyNameAr;
    private String tradingName;
    private String tradingNameAr;
    @NotNull
    @Positive
    private Long primaryIndustry;
    private String size;
    @NotNull
    private LocalDate incorporationDate;
    @NotNull
    @Positive
    private Long preferredCurrency;
    @NotNull
    @NotBlank
    private String address;
    @Pattern(regexp = "\\+?\\p{Digit}+$", message = "Must contain a phone number")
    private String telephoneNumber;
    @NotNull
    @NotBlank
    @Email
    private String email;
    private String fax;
    @Pattern(regexp = "\\+?\\p{Digit}+$", message = "Must contain a phone number")
    private String phone;
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d[1-40]+$", message = "Must contain a number")
    private String vatNumber;
    private String website;
    private String commercialRegister;
    private Boolean local;
    private Long cityId;

}
