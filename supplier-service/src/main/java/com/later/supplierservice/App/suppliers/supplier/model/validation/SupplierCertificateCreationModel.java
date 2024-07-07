package com.later.supplierservice.App.suppliers.supplier.model.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SupplierCertificateCreationModel {
    @NotNull(message = "must select a certificate type")
    @Positive
    private Long certificateType;
    private String attachment;
    private LocalDate expiryDate;
}
