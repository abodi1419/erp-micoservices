package com.later.supplierservice.App.suppliers.controller;

import com.later.supplierservice.App.suppliers.supplier.model.validation.SupplierContactCreationModel;
import com.later.supplierservice.App.suppliers.supplier.service.SupplierContactService;
import com.later.supplierservice.constants.ApiResponse;
import com.later.supplierservice.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/supplier/contact")
@RequiredArgsConstructor
public class SupplierContactController {

    private final SupplierContactService supplierContactService;

    @PostMapping("create/{supplierId}")
    public ResponseEntity create(@PathVariable Long supplierId, @Valid @RequestBody SupplierContactCreationModel supplierContactCreationModel) throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierContactService.create(supplierContactCreationModel, supplierId))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody SupplierContactCreationModel supplierContactCreationModel) throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierContactService.update(supplierContactCreationModel, id))
        );
    }

    @GetMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Long id)
            throws ApiException {
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success",
                        supplierContactService.delete(id))
        );
    }
}
