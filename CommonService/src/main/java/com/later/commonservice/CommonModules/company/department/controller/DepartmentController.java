package com.later.commonservice.CommonModules.company.department.controller;


import com.later.commonservice.CommonModules.company.department.service.DepartmentService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    @GetMapping("list")
    public ResponseEntity list(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) Long costCenterId) {
        if (id != null && costCenterId == null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", departmentService.findByIdOrElseNull(id))
            );
        } else if (id!=null && costCenterId!=null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", departmentService
                            .findByIdUnderCostCenter(id,costCenterId))
            );
        } else if (costCenterId!=null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", departmentService.findAllByCostCenter(costCenterId))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", departmentService.findAll())
        );
    }
}
