package com.later.erp.App.procurement.ApprovalService.repository;

import com.later.erp.App.procurement.ApprovalService.entity.ProcurementActiveApproval;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcurementActiveApprovalRepo extends JpaRepository<ProcurementActiveApproval, Long> {
    List<ProcurementActiveApproval> getAllBySystemCodeAndRefId(String systemCode, Long id);

    @Modifying
    @Transactional
    void deleteByRefIdAndSystemCode(Long id, String systemCode);
}
