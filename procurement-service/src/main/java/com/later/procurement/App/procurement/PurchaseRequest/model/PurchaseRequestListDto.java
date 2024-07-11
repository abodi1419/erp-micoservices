package com.later.procurement.App.procurement.PurchaseRequest.model;

import com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequest;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link PurchaseRequest}
 */
public record PurchaseRequestListDto(Long id, String costCenterName, String costCenterNameAr, String costCenterRefCode,
                                     String departmentName, String departmentNameAr, String departmentRefCode,
                                     String divisionName, String divisionNameAr, String disciplineName,
                                     String disciplineNameAr, String disciplineCode, LocalDate deliveryDate,
                                     Boolean service, String refCode, Long createdById, String createdByName,
                                     String createdByNameAr, String createdByCompanyNumber, Short status, Long buyerId,
                                     String buyerName, String buyerNameAr, String buyerCompanyNumber,
                                     LocalDateTime createdAt) implements Serializable {
}