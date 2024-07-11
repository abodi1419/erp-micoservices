package com.later.procurement.App.procurement.PurchaseRequest.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.later.procurement.App.procurement.PurchaseOrder.entity.PurchaseOrder}
 */
public record PurchaseOrderDto(String refCode, Short status, LocalDateTime createdAt) implements Serializable {
}