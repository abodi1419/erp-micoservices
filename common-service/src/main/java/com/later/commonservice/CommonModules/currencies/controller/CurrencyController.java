package com.later.commonservice.CommonModules.currencies.controller;


import com.later.commonservice.CommonModules.currencies.service.CurrencyService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("list")
    public ResponseEntity listCurrencies(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", currencyService.findByIdOrElseNull(id))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", currencyService.findAll())
        );
    }
}
