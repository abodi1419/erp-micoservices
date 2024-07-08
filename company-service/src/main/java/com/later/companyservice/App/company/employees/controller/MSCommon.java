package com.later.companyservice.App.company.employees.controller;

import com.later.companyservice.App.company.employees.service.EmployeeService;
import com.later.companyservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ms")
@RequiredArgsConstructor
public class MSCommon {

    private final EmployeeService employeeService;

    @GetMapping("employees/list")
    public ResponseEntity getItems(@RequestParam(required = false) Long id, @RequestParam(required = false) Boolean shortModel) {
        if (id != null) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", employeeService.findByIdOrElseNull(id))
            );
        }
        if (shortModel != null && shortModel) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", employeeService.listShort())
            );
        }
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", employeeService.findAll())
        );
    }

    @PostMapping("employees/list")
    public ResponseEntity getItems(@RequestBody List<Long> employeeIds) {
        if (employeeIds != null && !employeeIds.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse(true, 200, "Success", employeeService.findAllById(employeeIds))
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", List.of())
        );
    }
}
