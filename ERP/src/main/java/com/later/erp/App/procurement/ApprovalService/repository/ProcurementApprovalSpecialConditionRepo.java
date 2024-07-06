package com.later.erp.App.procurement.ApprovalService.repository;

import com.later.erp.App.procurement.ApprovalService.entity.ProcurementApprovalSpecialCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcurementApprovalSpecialConditionRepo extends JpaRepository<ProcurementApprovalSpecialCondition, Long> {
    List<ProcurementApprovalSpecialCondition> findAllBySystemCode(String systemCode);

    List<ProcurementApprovalSpecialCondition> findAllBySystemCodeAndStatus(String systemCode, Short status);
}
