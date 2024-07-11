package com.later.procurement.App.procurement.ApprovalService.model;

import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementApprovalSpecialCondition;

import java.io.Serializable;

/**
 * DTO for {@link ProcurementApprovalSpecialCondition}
 */
public record ProcurementApprovalSpecialConditionDto(String type, String typeAr, Integer typeId, String typeDesc,
                                                     String typeDescAr, Short status,
                                                     Boolean active) implements Serializable {
}