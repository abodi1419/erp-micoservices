package com.later.procurement.App.procurement.ApprovalService.model;

import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementActiveApproval;

import java.io.Serializable;

/**
 * DTO for {@link ProcurementActiveApproval}
 */
public record ProcurementActiveApprovalDto(Long approverId, String approverName, String approverNameAr,
                                           String approverCompanyNumber, Short status) implements Serializable {
}