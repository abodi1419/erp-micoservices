package com.later.procurement.App.procurement.PurchaseRequest.mapper;

import com.later.procurement.App.procurement.PurchaseOrder.entity.PurchaseOrder;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseOrderDto;

public class PurchaseOrderMapper {
    public static PurchaseOrderDto toDto(PurchaseOrder purchaseOrder) {
        if (purchaseOrder == null) {
            return null;
        }
        return new PurchaseOrderDto(purchaseOrder.getRefCode(), purchaseOrder.getStatus(), purchaseOrder.getCreatedAt());
    }
}
