package com.later.commonservice.CommonModules.banks.controller;


import com.later.commonservice.CommonModules.banks.service.BankService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @GetMapping("list")
    public ResponseEntity GET(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", bankService.findByIdOrElseNull(id))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", bankService.findAll())
        );
    }
}
