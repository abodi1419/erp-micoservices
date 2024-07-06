package com.later.erp.App.procurement.PurchaseRequest.repository;

import com.later.erp.App.procurement.PurchaseRequest.entity.PurchaseRequest;
import com.later.erp.App.procurement.PurchaseRequest.model.PurchaseRequestShortModel;
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
    @Query("select new com.later.erp.App.procurement.PurchaseRequest.model.PurchaseRequestShortModel( " +
            " p.id, CONCAT(p.costCenterRefCode, ' - ', p.costCenterName), CONCAT(p.costCenterRefCode, ' - ', p.costCenterNameAr), " +
            " CONCAT(p.divisionName, ' - ', p.divisionNameAr), CONCAT(p.disciplineCode, ' - ', p.disciplineName)," +
            " CONCAT(p.disciplineCode, ' - ', p.disciplineNameAr), p.refCode, CONCAT(p.createdByCompanyNumber,' - ', p.createdByName), " +
            " CONCAT(p.createdByCompanyNumber,' - ', p.createdByNameAr), p.createdAt, p.deliveryDate, p.total)" +
            " from PurchaseRequest p where p.buyerId=:employeeId and p.status=50 and p.costCenterId=:costCenterId " +
            " order by p.id desc ")
    List<PurchaseRequestShortModel> listForBuyer(Long employeeId, Long costCenterId);
}
