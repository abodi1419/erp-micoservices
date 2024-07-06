package com.later.supplierservice.App.suppliers.controller;

import com.later.supplierservice.Security.Auth.entities.Employee;
import com.later.supplierservice.App.suppliers.supplier.model.validation.SupplierCreationModel;
import com.later.supplierservice.App.suppliers.supplier.service.SupplierService;
import com.later.supplierservice.Security.Auth.entities.LoginUser;
import com.later.supplierservice.constants.ApiResponse;
import com.later.supplierservice.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RestController
@RequestMapping("api/v1/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("")
    public ResponseEntity list(@RequestParam String filter) throws ApiException {
        Employee employee = ((LoginUser) getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.findAll(filter, employee))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.findById(id))
        );
    }

    @GetMapping("submit/{supplierId}")
    public ResponseEntity submit(@PathVariable Long supplierId) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.submit(supplierId))
        );
    }

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody SupplierCreationModel supplierCreationModel) throws ApiException {
        Employee loginUser = ((LoginUser) getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.create(supplierCreationModel, loginUser))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody SupplierCreationModel supplierCreationModel) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.update(supplierCreationModel, id, loginUser))
        );
    }

    @PostMapping("approve/{id}")
    public ResponseEntity approve(@PathVariable Long id, @RequestBody(required = false) HashMap<String, String> map) throws ApiException {
        if (map == null) {
            map = new HashMap<>();
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.approve(map, id))
        );
    }

    @PostMapping("reject/{id}")
    public ResponseEntity reject(@PathVariable Long id, @RequestBody(required = false) HashMap<String, String> map) throws ApiException {
        if (map == null) {
            map = new HashMap<>();
        }
        Employee employee = ((LoginUser) getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.reject(id, employee, map))
        );
    }

    @GetMapping("list-industry-types")
    public ResponseEntity listIndustryTypes() {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.getIndustryTypes())
        );
    }

    @GetMapping("list-currencies")
    public ResponseEntity listCurrencies() {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.getCurrencies())
        );
    }

    @GetMapping("list-banks")
    public ResponseEntity listBanks() {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.getBanks())
        );
    }

    @GetMapping("list-certificate-types")
    public ResponseEntity listCertificateTypes() {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.getCertificateTypes())
        );
    }

    @GetMapping("list-term-types")
    public ResponseEntity listTermTypes() {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.getTermTypes())
        );
    }

    @GetMapping("list-business-types")
    public ResponseEntity listBusinessTypes() {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.getBusinessTypes())
        );
    }

    @GetMapping("list-countries")
    public ResponseEntity listCountries() {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.getCountries())
        );
    }

    @GetMapping("list-cities/{countryId}")
    public ResponseEntity listCities(@PathVariable Long countryId) {
        return ok().body(
                new ApiResponse(true, 200, "Success", supplierService.getCities(countryId))
        );
    }


}
