package com.later.supplierservice.App.suppliers.ApprovalService.repository;

import com.later.supplierservice.App.suppliers.ApprovalService.entity.SupplierActiveApproval;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierActiveApprovalRepo extends JpaRepository<SupplierActiveApproval, Long> {
    List<SupplierActiveApproval> getAllByRefIdAndSystemCode(Long id, String systemCode);

    @Modifying
    @Transactional
    void deleteAllByRefIdAndSystemCode(Long id, String systemCode);
}
