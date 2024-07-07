package com.later.procurement.App.procurement.PurchaseRequest.controller;


import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestCreationModel;
import com.later.procurement.App.procurement.PurchaseRequest.service.PurchaseRequestService;
import com.later.procurement.CommonModules.company.costCenter.entity.CostCenter;
import com.later.procurement.Exception.ApiException;
import com.later.procurement.Security.Auth.entities.Employee;
import com.later.procurement.Security.Auth.entities.LoginUser;
import com.later.procurement.constants.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/purchase-request")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('purchase-request.read')")
public class PurchaseRequestController {

    private final PurchaseRequestService purchaseRequestService;

    @GetMapping("")
    public ResponseEntity listPurchaseRequests() {
        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseRequestService.findAll())
        );
    }

    @GetMapping("{id}")
    public ResponseEntity listById(@PathVariable Long id) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();


        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseRequestService.findById(id, loginUser))
        );
    }

    @GetMapping("list-cost-centers")
    public ResponseEntity listCostCenters() throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.getCostCenters(loginUser))
        );
    }

    @GetMapping("list-departments")
    public ResponseEntity listDepartments(@RequestParam Long costCenterId) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.getDepartmentsUnderCostCenter(costCenterId, loginUser))
        );
    }

    @GetMapping("list-divisions")
    public ResponseEntity listDivisions(@RequestParam Long departmentId) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.getDivisionsUnderDepartment(departmentId, loginUser))
        );
    }

    @GetMapping("list-delivery-locations")
    public ResponseEntity listDeliveryLocations() throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.getDeliveryLocations(loginUser))
        );
    }

    @GetMapping("list-disciplines")
    public ResponseEntity listDisciplines() throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.getDisciplines(loginUser))
        );
    }

    @GetMapping("list-items")
    public ResponseEntity listItems(@RequestParam Long disciplineId) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseRequestService.getItems(disciplineId, loginUser))
        );
    }

    @GetMapping("check-approval/{divisionId}/{disciplineId}/{isService}")
    public ResponseEntity checkApproval(@PathVariable Long divisionId, @PathVariable Long disciplineId,
                                        @PathVariable Boolean isService) throws ApiException {
        return ok().body(
                new ApiResponse(true, 200, "Success",
                        (Object) purchaseRequestService.checkApproval(divisionId, disciplineId, isService))
        );
    }

    @GetMapping("list-employees")
    public ResponseEntity listEmployees() {
        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseRequestService.listEmployees())
        );
    }

    @GetMapping("download-document/{id}/{attachmentName}")
    public ResponseEntity downloadAttachment(@PathVariable Long id, @PathVariable String attachmentName) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();


        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseRequestService.downloadAttachment(id, attachmentName, loginUser))
        );
    }

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody PurchaseRequestCreationModel purchaseRequestCreationModel) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.create(purchaseRequestCreationModel, loginUser))
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody PurchaseRequestCreationModel purchaseRequestCreationModel) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.update(id, purchaseRequestCreationModel, loginUser))
        );
    }

    @PostMapping("approve/{id}")
    public ResponseEntity approve(@PathVariable Long id, @RequestBody(required = false) HashMap<String, String> map) throws ApiException {
        if (map == null) {
            map = new HashMap<>();
        }
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.approve(id, map, loginUser))
        );
    }

    @PostMapping("reject/{id}")
    public ResponseEntity reject(@PathVariable Long id, @RequestBody(required = false) HashMap<String, String> map) throws ApiException {
        if (map == null) {
            map = new HashMap<>();
        }
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        return ok().body(
                new ApiResponse(true, 200, "Success",
                        purchaseRequestService.reject(id, map, loginUser))
        );
    }
}
