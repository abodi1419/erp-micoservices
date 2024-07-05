package com.later.commonservice.CommonModules.termsTypes.controller;

import com.later.commonservice.CommonModules.termsTypes.service.TermTypeService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/term-types")
@RequiredArgsConstructor
public class TermTypeController {
    private final TermTypeService termTypeService;

    @GetMapping("list")
    public ResponseEntity GET(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", termTypeService.findByIdOrElseNull(id))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", termTypeService.findAll())
        );
    }
}
