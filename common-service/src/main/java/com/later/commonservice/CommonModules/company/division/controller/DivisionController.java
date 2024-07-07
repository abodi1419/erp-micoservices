package com.later.commonservice.CommonModules.company.division.controller;


import com.later.commonservice.CommonModules.company.division.service.DivisionService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/divisions")
@RequiredArgsConstructor
public class DivisionController {
    private final DivisionService divisionService;

    @GetMapping("list")
    public ResponseEntity list(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) Long departmentId) {
        if (id != null && departmentId == null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", divisionService.findByIdOrElseNull(id))
            );
        } else if (id!=null && departmentId!=null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", divisionService
                            .findByIdUnderDepartment(id,departmentId))
            );
        } else if (departmentId!=null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", divisionService.findAllUnderDepartment(departmentId))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", divisionService.findAll())
        );
    }

}
