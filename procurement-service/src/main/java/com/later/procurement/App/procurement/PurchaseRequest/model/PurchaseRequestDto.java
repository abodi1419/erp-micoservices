package com.later.procurement.App.procurement.PurchaseRequest.model;

import com.later.procurement.App.procurement.ApprovalService.model.ProcurementActiveApprovalDto;
import com.later.procurement.App.procurement.ApprovalService.model.ProcurementApprovalSpecialConditionDto;
import com.later.procurement.App.procurement.DocumentService.model.ProcurementDocumentDto;
import com.later.procurement.App.procurement.commentService.model.ProcurementCommentDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequest}
 */
public record PurchaseRequestDto(Long costCenterId, String costCenterName, String costCenterNameAr,
                                 String costCenterRefCode, Long departmentId, String departmentName,
                                 String departmentNameAr, String departmentRefCode, Long divisionId,
                                 String divisionName, String divisionNameAr, Long disciplineId, String disciplineName,
                                 String disciplineNameAr, String disciplineCode, Boolean permanentItems,
                                 LocalDate deliveryDate, List<ProcurementDocumentDto> documents,
                                 List<ProcurementCommentDto> comments, List<ProcurementActiveApprovalDto> approval,
                                 String generalRemarks, String deliveryTerms, Boolean service, Double total,
                                 LocalDateTime lastApprovalDate, String refCode, Long createdById, String createdByName,
                                 String createdByNameAr, String createdByCompanyNumber, Short status,
                                 List<PurchaseRequestDetailsDto> purchaseRequestDetails, Long buyerId, String buyerName,
                                 String buyerNameAr, String buyerCompanyNumber, LocalDateTime createdAt,
                                 LocalDateTime updatedAt,
                                 List<PurchaseOrderDto> purchaseOrders,
                                 List<ProcurementApprovalSpecialConditionDto> specialConditions) implements Serializable {
}