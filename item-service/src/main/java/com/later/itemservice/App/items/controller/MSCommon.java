package com.later.itemservice.App.items.controller;

import com.later.itemservice.App.items.service.ItemCategoryService;
import com.later.itemservice.App.items.service.ItemService;
import com.later.itemservice.App.items.service.MeasureUnitService;
import com.later.itemservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ms")
@RequiredArgsConstructor
public class MSCommon {
    private final ItemService itemService;
    private final ItemCategoryService itemCategoryService;
    private final MeasureUnitService measureUnitService;

    @GetMapping("items/list")
    public ResponseEntity getItems(@RequestParam(required = false) Long id, @RequestParam(required = false) Long disciplineId) {
        if (id != null && disciplineId == null) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", itemService.findByIdOrElseNull(id))
            );
        }
        if (id != null && disciplineId != null) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", itemService.findByIdUnderDiscipline(id, disciplineId))
            );
        }
        if (disciplineId != null) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", itemService
                            .findAllUnderDiscipline(disciplineId))
            );
        }
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", itemService.findAll())
        );
    }

    @PostMapping("items/list")
    public ResponseEntity getItems(@RequestBody List<Long> itemIds, @RequestParam(required = false) Long disciplineId) {
        if (itemIds != null && !itemIds.isEmpty() && disciplineId == null) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", itemService.findAllById(itemIds))
            );
        }
        if (itemIds != null && !itemIds.isEmpty() && disciplineId != null) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", itemService
                            .findAllByIdUnderDiscipline(itemIds, disciplineId))
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", List.of())
        );
    }

    @GetMapping("item-categories/list")
    public ResponseEntity getItemCategories(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", itemCategoryService.findByIdOrElseNull(id))
            );
        }
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", itemCategoryService.findAll())
        );
    }

    @PostMapping("item-categories/list")
    public ResponseEntity getItemCategories(@RequestBody List<Long> itemCategoriesIds) {
        if (itemCategoriesIds != null && !itemCategoriesIds.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", itemCategoryService.findAllById(itemCategoriesIds))
            );
        }
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", List.of())
        );
    }

    @GetMapping("measure-units/list")
    public ResponseEntity getMeasureUnits(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", measureUnitService.findByIdOrElseNull(id))
            );
        }
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", itemCategoryService.findAll())
        );
    }

    @PostMapping("measure-units/list")
    public ResponseEntity getMeasureUnits(@RequestBody List<Long> itemCategoriesIds) {
        if (itemCategoriesIds != null && !itemCategoriesIds.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", itemCategoryService.findAllById(itemCategoriesIds))
            );
        }
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", List.of())
        );
    }
}
