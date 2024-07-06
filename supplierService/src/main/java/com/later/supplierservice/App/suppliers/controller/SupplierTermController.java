package com.later.supplierservice.App.suppliers.controller;

import com.later.supplierservice.App.suppliers.supplier.model.validation.SupplierTermCreationModel;
import com.later.supplierservice.App.suppliers.supplier.service.SupplierTermsService;
import com.later.supplierservice.constants.ApiResponse;
import com.later.supplierservice.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/supplier/terms")
@RequiredArgsConstructor
public class SupplierTermController {
    private final SupplierTermsService supplierTermsService;

    @PostMapping("create/{supplierId}")
    public ResponseEntity<ApiResponse> create(@PathVariable Long supplierId,
                                              @Valid @RequestBody SupplierTermCreationModel supplierTermCreationModel) throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierTermsService.create(supplierTermCreationModel, supplierId))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id,
                                              @Valid @RequestBody SupplierTermCreationModel supplierTermCreationModel) throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierTermsService.update(supplierTermCreationModel, id))
        );
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierTermsService.delete(id))
        );
    }
}
