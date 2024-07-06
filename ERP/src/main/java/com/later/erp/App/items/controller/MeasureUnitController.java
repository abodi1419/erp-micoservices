package com.later.erp.App.items.controller;

import com.later.erp.App.items.entity.MeasureUnit;
import com.later.erp.App.items.service.MeasureUnitService;
import com.later.erp.constants.ApiResponse;
import com.later.erp.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/measure-unit")
@RequiredArgsConstructor
public class MeasureUnitController {
    private final MeasureUnitService measureUnitService;

    @GetMapping("")
    public ResponseEntity getUnitMeasures(@RequestParam(name = "product", required = false) Boolean product) {
        return ok().body(
                new ApiResponse(true, 200, "Success", measureUnitService.findAll(product))
        );
    }


    @GetMapping("{id}")
    public ResponseEntity getUnitMeasure(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", measureUnitService.findById(id))
        );
    }

    @PostMapping("create")
    public ResponseEntity createUnitMeasure(@RequestBody @Valid MeasureUnit measureUnit) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", measureUnitService.create(measureUnit))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity updateUnitMeasure(@PathVariable Long id, @RequestBody @Valid MeasureUnit measureUnit) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", measureUnitService.update(id, measureUnit))
        );
    }
}
