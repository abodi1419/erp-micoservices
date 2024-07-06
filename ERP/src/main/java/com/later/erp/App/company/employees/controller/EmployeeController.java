package com.later.erp.App.company.employees.controller;

import com.later.erp.App.company.employees.model.EmployeeCreationModel;
import com.later.erp.App.company.employees.service.EmployeeService;
import com.later.erp.constants.ApiResponse;
import com.later.erp.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.ok().body(new ApiResponse(true, 200, "Success", employeeService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity getAllEmployees(@PathVariable Long id) throws ApiException {
        return ResponseEntity.ok().body(new ApiResponse(true, 200, "Success", employeeService.findById(id)));
    }

    @PostMapping("create")
    public ResponseEntity createEmployee(@RequestBody @Valid EmployeeCreationModel employeeCreationModel) throws ApiException {
        return ResponseEntity.ok(new ApiResponse(true, 200, "Success",
                employeeService.create(employeeCreationModel)));
    }

    @PostMapping("update/{id}")
    public ResponseEntity createEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeCreationModel employeeCreationModel) throws ApiException {
        return ResponseEntity.ok(new ApiResponse(true, 200, "Success",
                employeeService.update(id, employeeCreationModel)));
    }

}
