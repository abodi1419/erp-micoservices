package com.later.erp.App.inventory.stocks.approvalService.repository;

import com.later.erp.App.inventory.stocks.approvalService.entity.StockActiveApproval;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockActiveApprovalRepo extends JpaRepository<StockActiveApproval, Long> {
    List<StockActiveApproval> getAllByRefIdAndSystemCode(Long id, String systemCode);

    @Modifying
    @Transactional
    void deleteAllByRefIdAndSystemCode(Long id, String systemCode);
}
