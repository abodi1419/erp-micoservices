package com.later.procurement.App.procurement.PurchaseRequest.repository;


import com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequest;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestListDto;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestShortModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRequestRepo extends JpaRepository<PurchaseRequest, Long> {
    @Query("select coalesce(max(x.serial), 0)+1 from PurchaseRequest x")
    Integer getCurrentSerial();

    //    Long id, String costCenter, String costCenterAr,
    //                                     String division, String discipline, String disciplineAr,
    //                                     String refCode, String createdBy, LocalDateTime createdAt, LocalDate deliveryDate, Double estTotal
    @Query("select new com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestShortModel( " +
            " p.id, CONCAT(p.costCenterRefCode, ' - ', p.costCenterName), CONCAT(p.costCenterRefCode, ' - ', p.costCenterNameAr), " +
            " CONCAT(p.divisionName, ' - ', p.divisionNameAr), CONCAT(p.disciplineCode, ' - ', p.disciplineName)," +
            " CONCAT(p.disciplineCode, ' - ', p.disciplineNameAr), p.refCode, CONCAT(p.createdByCompanyNumber,' - ', p.createdByName), " +
            " CONCAT(p.createdByCompanyNumber,' - ', p.createdByNameAr), p.createdAt, p.deliveryDate, p.total)" +
            " from PurchaseRequest p where p.buyerId=:employeeId and p.status=50 and p.costCenterId=:costCenterId " +
            " order by p.id desc ")
    List<PurchaseRequestShortModel> listForBuyer(Long employeeId, Long costCenterId);

    @Query("select new com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestListDto(" +
            "pr.id, pr.costCenterName, pr.costCenterNameAr, pr.costCenterRefCode, " +
            "pr.departmentName, pr.departmentNameAr, pr.departmentRefCode, pr.divisionName, pr.divisionNameAr," +
            "pr.disciplineName, pr.disciplineNameAr, pr.disciplineCode, pr.deliveryDate," +
            "pr.service,pr.refCode, pr.createdById, pr.createdByName, pr.createdByNameAr, pr.createdByCompanyNumber," +
            "pr.status, pr.buyerId, pr.buyerName, pr.buyerNameAr, pr.buyerCompanyNumber, pr.createdAt) " +
            " from PurchaseRequest pr left join ProcurementActiveApproval pa on pa.refCode=pr.refCode " +
            " where pr.createdById=:employeeId or " +
            " :employeeId in (pa.approverId) " +
            " order by pr.createdAt desc ")
    List<PurchaseRequestListDto> listPurchaseRequests(Long employeeId);

//    Long id, String costCenterName, String costCenterNameAr, String costCenterRefCode,
//                                     String departmentName, String departmentNameAr, String departmentRefCode,
//                                     String divisionName, String divisionNameAr, String disciplineName,
//                                     String disciplineNameAr, String disciplineCode, LocalDate deliveryDate,
//                                     Boolean service, String refCode, Long createdById, String createdByName,
//                                     String createdByNameAr, String createdByCompanyNumber, Short status, Long buyerId,
//                                     String buyerName, String buyerNameAr, String buyerCompanyNumber,
//                                     LocalDateTime createdAt
}
