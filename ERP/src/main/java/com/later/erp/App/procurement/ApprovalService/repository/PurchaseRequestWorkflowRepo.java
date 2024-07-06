package com.later.erp.App.procurement.ApprovalService.repository;

import com.later.erp.App.procurement.ApprovalService.entity.PurchaseRequestWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRequestWorkflowRepo extends JpaRepository<PurchaseRequestWorkflow, Long> {
    List<PurchaseRequestWorkflow> findByDivisionIdAndDisciplineId(Long id, Long id1);
}
