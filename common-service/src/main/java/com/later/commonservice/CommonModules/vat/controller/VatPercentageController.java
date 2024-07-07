package com.later.commonservice.CommonModules.vat.controller;


import com.later.commonservice.CommonModules.vat.service.VatPercentageService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/vat-percentages")
@RequiredArgsConstructor
public class VatPercentageController {

    private final VatPercentageService vatPercentageService;

    @GetMapping("list")
    public ResponseEntity list(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", vatPercentageService.findByIdOrElseNull(id))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", vatPercentageService.findAll())
        );
    }
}
