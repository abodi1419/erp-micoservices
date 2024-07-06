package com.later.erp.App.items.controller;

import com.later.erp.App.company.employees.entity.Employee;
import com.later.erp.App.company.employees.repository.EmployeeRepo;
import com.later.erp.App.items.model.ItemCreationModel;
import com.later.erp.App.items.service.ItemCategoryService;
import com.later.erp.App.items.service.ItemService;
import com.later.erp.App.items.service.MeasureUnitService;
import com.later.erp.constants.ApiResponse;
import com.later.erp.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemCategoryService itemCategoryService;
    private final MeasureUnitService measureUnitService;
    private final EmployeeRepo employeeRepo;

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
        Employee login = employeeRepo.findById(1L).orElse(null);
        return ok().body(
                new ApiResponse(true, 200, "Success", itemService.create(itemCreationModel, login))
        );
    }

    @PostMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody ItemCreationModel itemCreationModel) throws ApiException {
        Employee login = employeeRepo.findById(1L).orElse(null);

        return ok().body(
                new ApiResponse(true, 200, "Success", itemService.update(id, itemCreationModel, login))
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
