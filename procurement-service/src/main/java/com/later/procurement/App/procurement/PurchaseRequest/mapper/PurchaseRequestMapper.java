package com.later.procurement.App.procurement.PurchaseRequest.mapper;

import com.later.procurement.App.procurement.ApprovalService.mapper.ProcurementActiveApprovalMapper;
import com.later.procurement.App.procurement.ApprovalService.mapper.ProcurementApprovalSpecialConditionsMapper;
import com.later.procurement.App.procurement.ApprovalService.model.ProcurementActiveApprovalDto;
import com.later.procurement.App.procurement.ApprovalService.model.ProcurementApprovalSpecialConditionDto;
import com.later.procurement.App.procurement.DocumentService.mapper.ProcurementDocumentMapper;
import com.later.procurement.App.procurement.DocumentService.model.ProcurementDocumentDto;
import com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequest;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseOrderDto;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestDetailsDto;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestDto;
import com.later.procurement.App.procurement.commentService.mapper.ProcurementCommentMapper;
import com.later.procurement.App.procurement.commentService.model.ProcurementCommentDto;

import java.util.List;

public class PurchaseRequestMapper {
    public static PurchaseRequestDto toDto(PurchaseRequest purchaseRequest) {
        if (purchaseRequest == null) {
            return null;
        }
        List<ProcurementDocumentDto> procurementDocumentDtos = purchaseRequest.getDocuments().stream()
                .map(ProcurementDocumentMapper::toDto).toList();
        List<ProcurementCommentDto> commentDtos = purchaseRequest.getComments().stream()
                .map(ProcurementCommentMapper::toDto).toList();
        List<ProcurementApprovalSpecialConditionDto> specialConditionDtos = purchaseRequest.getSpecialConditions()
                .stream().map(ProcurementApprovalSpecialConditionsMapper::toDto).toList();
        List<ProcurementActiveApprovalDto> approvalDtos = purchaseRequest.getApproval().stream()
                .map(ProcurementActiveApprovalMapper::toDto).toList();
        List<PurchaseRequestDetailsDto> detailsDtos = purchaseRequest.getPurchaseRequestDetails().stream()
                .map(PurchaseRequestDetailsMapper::toDto).toList();
        List<PurchaseOrderDto> purchaseOrderDtos = purchaseRequest.getPurchaseOrders().stream()
                .map(PurchaseOrderMapper::toDto).toList();
        return new PurchaseRequestDto(purchaseRequest.getCostCenterId(), purchaseRequest.getCostCenterName(), purchaseRequest.getCostCenterNameAr(),
                purchaseRequest.getCostCenterRefCode(), purchaseRequest.getDepartmentId(), purchaseRequest.getDepartmentName(),
                purchaseRequest.getDepartmentNameAr(), purchaseRequest.getDepartmentRefCode(), purchaseRequest.getDivisionId(),
                purchaseRequest.getDivisionName(), purchaseRequest.getDivisionNameAr(), purchaseRequest.getDisciplineId(),
                purchaseRequest.getDisciplineName(), purchaseRequest.getDisciplineNameAr(), purchaseRequest.getDisciplineCode(),
                purchaseRequest.getPermanentItems(), purchaseRequest.getDeliveryDate(), procurementDocumentDtos, commentDtos, approvalDtos,
                purchaseRequest.getGeneralRemarks(), purchaseRequest.getDeliveryTerms(), purchaseRequest.getService(),
                purchaseRequest.getTotal(), purchaseRequest.getLastApprovalDate(), purchaseRequest.getRefCode(), purchaseRequest.getCreatedById(),
                purchaseRequest.getCreatedByName(), purchaseRequest.getCreatedByNameAr(), purchaseRequest.getCreatedByCompanyNumber(),
                purchaseRequest.getStatus(), detailsDtos, purchaseRequest.getBuyerId(), purchaseRequest.getBuyerName(),
                purchaseRequest.getBuyerNameAr(), purchaseRequest.getBuyerCompanyNumber(), purchaseRequest.getCreatedAt(),
                purchaseRequest.getUpdatedAt(), purchaseOrderDtos, specialConditionDtos);
    }
}
