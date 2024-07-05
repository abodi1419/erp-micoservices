package com.later.commonservice.CommonModules.industryTypes.controller;


import com.later.commonservice.CommonModules.industryTypes.service.IndustryTypeService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/industry-types")
@RequiredArgsConstructor
public class IndustryTypeController {
    private final IndustryTypeService industryTypeService;

    @GetMapping("list")
    public ResponseEntity listDisciplines(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", industryTypeService.findByIdOrElseNull(id))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", industryTypeService.findAll())
        );
    }
}
