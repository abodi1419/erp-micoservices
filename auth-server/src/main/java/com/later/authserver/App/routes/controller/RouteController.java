package com.later.authserver.App.routes.controller;


import com.later.authserver.App.routes.model.RouteCreationModel;
import com.later.authserver.App.routes.service.RouteService;
import com.later.authserver.Exception.ApiException;
import com.later.authserver.Security.Auth.entities.Employee;
import com.later.authserver.Security.Auth.entities.LoginUser;
import com.later.authserver.constants.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody RouteCreationModel routeCreationModel) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", routeService.create(routeCreationModel, loginUser))
        );
    }
}
