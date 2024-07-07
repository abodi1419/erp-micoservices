package com.later.customerservice.App.customer.controller;

import com.later.customerservice.App.customer.model.CustomerCreationModel;
import com.later.customerservice.App.customer.service.CustomerService;
import com.later.customerservice.Exception.ApiException;
import com.later.customerservice.constants.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("")
    public ResponseEntity getAll() {
        return ok().body(
                new ApiResponse(true, 200, "Success", customerService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", customerService.findById(id))
        );
    }

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody CustomerCreationModel customerCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", customerService.create(customerCreationModel))
        );
    }

    @PostMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody CustomerCreationModel customerCreationModel) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", customerService.update(id, customerCreationModel))
        );
    }
}
