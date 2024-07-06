package com.later.erp.App.suppliers.supplier.model.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierBankAccountCreationModel {
    @NotNull(message = "must select a bank")
    @Positive(message = "Not found")
    private Long bankId;
    @NotNull()
    @NotBlank
    private String accountIBAN;
    private String accountNo;
    @NotNull
    @NotBlank
    private String accountName;
    @NotNull
    @NotBlank
    private String accountNameAr;
    private Boolean preferred;
}
