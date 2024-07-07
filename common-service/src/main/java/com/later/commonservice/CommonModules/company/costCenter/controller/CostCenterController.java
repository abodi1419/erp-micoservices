package com.later.commonservice.CommonModules.company.costCenter.controller;


import com.later.commonservice.CommonModules.company.costCenter.service.CostCenterService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/cost-centers")
@RequiredArgsConstructor
public class CostCenterController {
    private final CostCenterService costCenterService;
    @GetMapping("list")
    public ResponseEntity list(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", costCenterService.findByIdOrElseNull(id))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", costCenterService.findAll())
        );
    }
}
