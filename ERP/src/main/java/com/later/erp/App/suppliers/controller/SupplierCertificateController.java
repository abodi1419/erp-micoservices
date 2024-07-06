package com.later.erp.App.suppliers.controller;

import com.later.erp.App.suppliers.supplier.model.validation.SupplierCertificateCreationModel;
import com.later.erp.App.suppliers.supplier.service.SupplierCertificateService;
import com.later.erp.constants.ApiResponse;
import com.later.erp.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/supplier/certificate")
@RequiredArgsConstructor
public class SupplierCertificateController {

    private final SupplierCertificateService supplierCertificateService;

    @GetMapping("deleted/{supplierId}")
    public ResponseEntity getAllDeleted(@PathVariable Long supplierId) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierCertificateService.findAllDeleted(supplierId))
        );
    }

    @GetMapping("retrieve/{id}")
    public ResponseEntity retrieveDeleted(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierCertificateService.retrieveDeleted(id))
        );
    }

    @PostMapping("create/{supplierId}")
    public ResponseEntity create(@PathVariable Long supplierId, @Valid @RequestBody SupplierCertificateCreationModel supplierCertificateCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierCertificateService
                        .create(supplierCertificateCreationModel, supplierId))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody SupplierCertificateCreationModel supplierCertificateCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierCertificateService
                        .update(supplierCertificateCreationModel, id))
        );
    }

    @GetMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierCertificateService.delete(id))
        );
    }

    @GetMapping("download/{id}")
    public ResponseEntity download(@PathVariable Long id, @RequestParam String fileName) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierCertificateService
                        .downloadCertificate(id, fileName))
        );
    }
}
