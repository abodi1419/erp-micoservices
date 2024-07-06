package com.later.supplierservice.App.suppliers.supplier.model.validation;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class SupplierTermCreationModel {
    @NotNull
    private Long termType;
    @NotBlank
    @NotNull
    @Size(min = 8)
    private String term;
    private String termAr;
}
