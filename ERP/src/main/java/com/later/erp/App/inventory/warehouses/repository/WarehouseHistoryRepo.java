package com.later.erp.App.inventory.warehouses.repository;

import com.later.erp.App.inventory.warehouses.entity.WarehouseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseHistoryRepo extends JpaRepository<WarehouseHistory, Long> {
}
