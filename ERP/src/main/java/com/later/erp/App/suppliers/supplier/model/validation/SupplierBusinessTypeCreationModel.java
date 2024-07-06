package com.later.erp.App.suppliers.supplier.model.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SupplierBusinessTypeCreationModel {
    @NotNull
    @Positive
    private Long businessType;
    private SupplierCertificateCreationModel supplierCertificateCreationModel;
}
