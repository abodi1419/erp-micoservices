package com.later.erp.App.superAdmin.controller;

import com.later.erp.App.company.employees.entity.Employee;
import com.later.erp.App.superAdmin.model.RouteCreationModel;
import com.later.erp.App.superAdmin.service.RouteService;
import com.later.erp.Authorization.Security.Auth.entities.LoginUser;
import com.later.erp.constants.ApiResponse;
import com.later.erp.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/super-admin/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
//
//    @PostMapping("create")
//    public ResponseEntity create(@Valid @RequestBody RouteCreationModel routeCreationModel) throws ApiException {
//        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
//        return ResponseEntity.ok().body(
//                new ApiResponse(true, 200, "Success", routeService.create(routeCreationModel, loginUser))
//        );
//    }
}
