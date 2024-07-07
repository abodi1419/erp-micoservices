package com.later.inventoryservice.App.inventory.warehouses.repository;

import com.later.inventoryservice.App.inventory.warehouses.entity.WarehouseDetailsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseDetailsHistoryRepo extends JpaRepository<WarehouseDetailsHistory, Long> {
}
