package com.later.erp.App.inventory.warehouses.controller;

import com.later.erp.App.inventory.warehouses.model.WarehouseCreationModel;
import com.later.erp.App.inventory.warehouses.service.WarehouseService;
import com.later.erp.constants.ApiResponse;
import com.later.erp.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
final WarehouseService warehouseService;

    @GetMapping("")
    public ResponseEntity list() {
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseService.findAll())
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseService.findById(id))
        );
    }

    @GetMapping("/list-city")
    public ResponseEntity cities() {
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseService.getCityList())
        );
    }

    @GetMapping("/cost-center")
    public ResponseEntity costCenter() {
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseService.getCostCenterList())
        );
    }

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody WarehouseCreationModel warehouseCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseService.create(warehouseCreationModel))
        );
    }

    @PostMapping("/update/{wareHouseId}")
    public ResponseEntity update(@PathVariable @Valid Long wareHouseId,@Valid @RequestBody WarehouseCreationModel warehouseCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseService.update(wareHouseId,warehouseCreationModel))
        );
    }


}
