package com.later.erp.App.suppliers.supplier.model.validation;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierContactCreationModel {
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String contactName;
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String contactNameAr;
    private String contactJobTitle;
    private String contactJobTitleAr;
    @NotNull
    @Email
    @Size(max = 255)
    private String contactEmail;
    @NotNull
    @Size(max = 20)
    @Pattern(regexp = "\\+?\\p{Digit}+$", message = "Must contain a phone number")
    private String contactPhone;
    private Long contactCityId;
    private Boolean preferred;
}
