package com.later.itemservice.App.items.controller;

import com.later.itemservice.App.items.model.ItemCreationModel;
import com.later.itemservice.App.items.service.ItemCategoryService;
import com.later.itemservice.App.items.service.ItemService;
import com.later.itemservice.App.items.service.MeasureUnitService;
import com.later.itemservice.Exception.ApiException;
import com.later.itemservice.Security.Auth.entities.Employee;
import com.later.itemservice.Security.Auth.entities.LoginUser;
import com.later.itemservice.constants.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemCategoryService itemCategoryService;
    private final MeasureUnitService measureUnitService;

    @GetMapping("")
    public ResponseEntity list() {
        return ok().body(
                new ApiResponse(true, 200, "Success", itemService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", itemService.findById(id))
        );
    }


    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody ItemCreationModel itemCreationModel) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", itemService.create(itemCreationModel, loginUser))
        );
    }

    @PostMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody ItemCreationModel itemCreationModel) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", itemService.update(id, itemCreationModel, loginUser))
        );
    }

    @GetMapping("get-measure-units")
    public ResponseEntity getUnitMeasures() {
        return ok().body(
                new ApiResponse(true, 200, "Success", measureUnitService.getMeasureUnitsForItem())
        );
    }

    @GetMapping("get-item-categories")
    public ResponseEntity getItemCategories() {
        return ok().body(
                new ApiResponse(true, 200, "Success", itemCategoryService.getCategoriesForItem())
        );
    }
}
