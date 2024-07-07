package com.later.commonservice.CommonModules.businessTypes.controller;


import com.later.commonservice.CommonModules.businessTypes.service.BusinessTypeService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/business-types")
@RequiredArgsConstructor
public class BusinessTypeController {
    private final BusinessTypeService businessTypeService;

    @GetMapping("list")
    public ResponseEntity listBusinessTypes(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success",
                            businessTypeService.findByIdOrElseNull(id))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", businessTypeService.findAll())
        );
    }
}
