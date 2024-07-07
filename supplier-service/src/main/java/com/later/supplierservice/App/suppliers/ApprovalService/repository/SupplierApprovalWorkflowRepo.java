package com.later.supplierservice.App.suppliers.ApprovalService.repository;

import com.later.supplierservice.App.suppliers.ApprovalService.entity.SupplierApprovalWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierApprovalWorkflowRepo extends JpaRepository<SupplierApprovalWorkflow, Long> {
}
