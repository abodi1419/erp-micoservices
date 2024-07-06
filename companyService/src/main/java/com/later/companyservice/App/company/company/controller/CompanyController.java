package com.later.companyservice.App.company.company.controller;

import com.later.companyservice.App.company.company.model.CompanyCreationModel;
import com.later.companyservice.App.company.company.service.CompanyService;
import com.later.companyservice.constants.ApiResponse;
import com.later.companyservice.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("")
    public ResponseEntity getCompany() throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", companyService.getCompany())
        );
    }

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody CompanyCreationModel companyCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", companyService.create(companyCreationModel))
        );
    }
}
