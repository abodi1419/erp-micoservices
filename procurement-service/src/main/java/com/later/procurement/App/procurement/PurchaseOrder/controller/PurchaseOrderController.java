package com.later.procurement.App.procurement.PurchaseOrder.controller;



import com.later.procurement.App.procurement.PurchaseOrder.service.PurchaseOrderService;
import com.later.procurement.Exception.ApiException;
import com.later.procurement.Security.Auth.entities.Employee;
import com.later.procurement.Security.Auth.entities.LoginUser;
import com.later.procurement.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RestController
@RequestMapping("api/v1/purchase-order")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('purchase-order.read')")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("")
    public ResponseEntity list() {
        Employee loginUser = ((LoginUser) getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseOrderService.findAll(loginUser))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ApiException {
        Employee loginUser = ((LoginUser) getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseOrderService.findById(id, loginUser))
        );
    }

    @GetMapping("list-purchase-requests/{costCenterId}")
    public ResponseEntity GET(@PathVariable Long costCenterId) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseOrderService.listPurchaseRequests(costCenterId, loginUser))
        );
    }

    @GetMapping("get-purchase-request/{purchaseRequestId}")
    public ResponseEntity getPurchaseRequest(@PathVariable Long purchaseRequestId) throws ApiException {
        Employee loginUser = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return ok().body(
                new ApiResponse(true, 200, "Success", purchaseOrderService.findPurchaseRequestById(purchaseRequestId, loginUser))
        );
    }


}
