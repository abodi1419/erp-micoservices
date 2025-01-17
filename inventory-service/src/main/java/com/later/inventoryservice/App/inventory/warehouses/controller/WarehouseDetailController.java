package com.later.inventoryservice.App.inventory.warehouses.controller;

import com.later.inventoryservice.App.inventory.warehouses.model.WarehouseDetailsCreationModel;
import com.later.inventoryservice.App.inventory.warehouses.service.WarehouseDetailService;
import com.later.inventoryservice.Exception.ApiException;
import com.later.inventoryservice.constants.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/warehouse-detail")
@RequiredArgsConstructor
public class WarehouseDetailController {

    private final WarehouseDetailService warehouseDetailService;

    @PostMapping("/create/{wareHouseId}")
    public ResponseEntity creationWareHouseDetails(@PathVariable @Valid Long wareHouseId, @Valid @RequestBody List<WarehouseDetailsCreationModel> warehouseDetailsCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseDetailService.creationWareHouseDetails(wareHouseId,warehouseDetailsCreationModel))
        );
    }


    @PostMapping("/update/{wareHouseId}")
    public ResponseEntity updateWareHouseDetails(@PathVariable @Valid Long wareHouseId,@Valid @RequestBody List<WarehouseDetailsCreationModel> warehouseDetailsCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseDetailService.updateWareHouseDetails(wareHouseId,warehouseDetailsCreationModel))
        );
    }

}
