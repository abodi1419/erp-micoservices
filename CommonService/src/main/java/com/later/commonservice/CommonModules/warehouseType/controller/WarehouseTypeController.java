package com.later.commonservice.CommonModules.warehouseType.controller;


import com.later.commonservice.CommonModules.warehouseType.entity.WarehouseType;
import com.later.commonservice.CommonModules.warehouseType.service.WarehouseTypeService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/warehouse-types")
@RequiredArgsConstructor
public class WarehouseTypeController {
    private final WarehouseTypeService warehouseTypeService;

    @GetMapping("list")
    public ResponseEntity list(@RequestParam(required = false) Long id, @RequestBody(required = false)List<Long> warehouseTypesIds) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", warehouseTypeService.findByIdOrElseNull(id))
            );
        }
        if(warehouseTypesIds != null && !warehouseTypesIds.isEmpty()) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", warehouseTypeService.findAllById(warehouseTypesIds))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", warehouseTypeService.findAll())
        );
    }
}
