package com.later.erp.App.inventory.stocks.approvalService.repository;

import com.later.erp.App.inventory.stocks.approvalService.entity.StockApprovalWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockApprovalWorkflowRepo extends JpaRepository<StockApprovalWorkflow, Long> {
}
