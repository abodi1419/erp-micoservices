package com.later.inventoryservice.App.inventory.stocks.stock.controller;


import com.later.inventoryservice.App.inventory.stocks.stock.model.StockCreationModel;
import com.later.inventoryservice.App.inventory.stocks.stock.service.StockServices;
import com.later.inventoryservice.Security.Auth.entities.Employee;
import com.later.inventoryservice.Security.Auth.entities.LoginUser;
import com.later.inventoryservice.Exception.ApiException;
import com.later.inventoryservice.constants.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockServices stockServices;

    @GetMapping("")
    public ResponseEntity list() {
        return ok().body(
                new ApiResponse(true, 200, "Success", stockServices.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", stockServices.findById(id, loginUser))
        );
    }

    @GetMapping("list-items")
    public ResponseEntity items() {
        return ok().body(
                new ApiResponse(true, 200, "Success", stockServices.getItems())
        );
    }

    @GetMapping("list-warehouses")
    public ResponseEntity warehouses() {
        return ok().body(
                new ApiResponse(true, 200, "Success", stockServices.getWarehouses())
        );
    }

    @GetMapping("list-warehouse-details/{warehouseId}")
    public ResponseEntity warehouseDetails(@PathVariable Long warehouseId) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", stockServices.getWarehouseDetails(warehouseId))
        );
    }


    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody StockCreationModel supplierCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", stockServices.create(supplierCreationModel))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody StockCreationModel supplierCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", stockServices.update(supplierCreationModel, id))
        );
    }


    @PostMapping("approve/{id}")
    public ResponseEntity approve(@PathVariable Long id, @RequestBody(required = false) HashMap<String, String> map) throws ApiException {
        if (map == null) {
            map = new HashMap<>();
        }
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        stockServices.approve(id, map, loginUser))
        );
    }

    @PostMapping("reject/{id}")
    public ResponseEntity reject(@PathVariable Long id, @RequestBody(required = false) HashMap<String, String> map) throws ApiException {
        if (map == null) {
            map = new HashMap<>();
        }
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        stockServices.reject(id, map, loginUser))
        );
    }

//    @GetMapping("download-document/{id}/{attachmentName}")
//    public ResponseEntity downloadAttachment(@PathVariable Long id, @PathVariable String attachmentName) throws ApiException {
//        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
//
//
//        return ok().body(
//                new ApiResponse(true, 200, "Success", stockServices.downloadAttachment(id, attachmentName, loginUser))
//        );
//    }
}
