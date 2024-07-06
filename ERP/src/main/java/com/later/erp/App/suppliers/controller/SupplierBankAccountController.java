package com.later.erp.App.suppliers.controller;

import com.later.erp.App.suppliers.supplier.model.validation.SupplierBankAccountCreationModel;
import com.later.erp.App.suppliers.supplier.service.SupplierBankAccountService;
import com.later.erp.constants.ApiResponse;
import com.later.erp.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/supplier/bank-account")
@RequiredArgsConstructor
public class SupplierBankAccountController {

    private final SupplierBankAccountService supplierBankAccountService;

    @PostMapping("create/{supplierId}")
    public ResponseEntity create(@PathVariable Long supplierId,
                                 @Valid @RequestBody SupplierBankAccountCreationModel supplierBankAccountCreationModel)
            throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierBankAccountService.create(supplierBankAccountCreationModel, supplierId))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @Valid @RequestBody SupplierBankAccountCreationModel supplierBankAccountCreationModel)
            throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierBankAccountService.update(supplierBankAccountCreationModel, id))
        );
    }

    @GetMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Long id)
            throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierBankAccountService.delete(id))
        );
    }
}
