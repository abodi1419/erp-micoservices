package com.later.itemservice.App.items.controller;

import com.later.itemservice.App.items.model.ItemCategoryCreationModel;
import com.later.itemservice.App.items.service.ItemCategoryService;
import com.later.itemservice.Exception.ApiException;
import com.later.itemservice.constants.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/item-category")
@RequiredArgsConstructor
public class ItemCategoryController {
    private final ItemCategoryService itemCategoryService;

    @GetMapping("")
    public ResponseEntity getItemCategories() {
        return ok().body(
                new ApiResponse(true, 200, "Success", itemCategoryService.findAll())
        );
    }


    @GetMapping("{id}")
    public ResponseEntity getItemCategory(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", itemCategoryService.findById(id))
        );
    }

    @PostMapping("create")
    public ResponseEntity createCategory(@RequestBody @Valid ItemCategoryCreationModel itemCategoryCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", itemCategoryService.create(itemCategoryCreationModel))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody @Valid ItemCategoryCreationModel itemCategoryCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", itemCategoryService.update(id, itemCategoryCreationModel))
        );
    }


}
