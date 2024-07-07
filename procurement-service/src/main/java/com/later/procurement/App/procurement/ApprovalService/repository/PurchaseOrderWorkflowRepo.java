package com.later.procurement.App.procurement.ApprovalService.repository;

import com.later.procurement.App.procurement.ApprovalService.entity.PurchaseOrderWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderWorkflowRepo extends JpaRepository<PurchaseOrderWorkflow, Long> {

    @Query("select w from PurchaseOrderWorkflow w where w.costCenterId=:costCenter and w.divisionId=:division " +
            " and w.pettyCash=:pettyCash and :total>=w.minValue and :total<=w.maxValue")
    List<PurchaseOrderWorkflow> findApproval(Long costCenter, Long division, Boolean pettyCash, Double total);
}
