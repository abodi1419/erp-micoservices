package com.later.erp.App.suppliers.controller;

import com.later.erp.App.suppliers.supplier.model.validation.SupplierBusinessTypeCreationModel;
import com.later.erp.App.suppliers.supplier.service.SupplierBusinessTypeService;
import com.later.erp.constants.ApiResponse;
import com.later.erp.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/supplier/business-type")
@RequiredArgsConstructor
public class SupplierBusinessTypeController {

    private final SupplierBusinessTypeService supplierBusinessTypeService;

    @PostMapping("create/{supplierId}")
    public ResponseEntity create(@PathVariable Long supplierId, @Valid @RequestBody SupplierBusinessTypeCreationModel supplierBusinessTypeCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierBusinessTypeService
                        .create(supplierBusinessTypeCreationModel, supplierId))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody SupplierBusinessTypeCreationModel supplierBusinessTypeCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierBusinessTypeService
                        .update(supplierBusinessTypeCreationModel, id))
        );
    }

    @GetMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierBusinessTypeService.delete(id))
        );
    }

    @GetMapping("download/{id}")
    public ResponseEntity download(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierBusinessTypeService
                        .downloadCertificate(id))
        );
    }
}
