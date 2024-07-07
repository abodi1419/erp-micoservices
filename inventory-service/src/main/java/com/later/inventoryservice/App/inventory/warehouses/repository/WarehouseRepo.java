package com.later.inventoryservice.App.inventory.warehouses.repository;

import com.later.inventoryservice.App.inventory.warehouses.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
    @Query("select coalesce(max(i.serial), 0)+1 from Warehouse i")
    Integer getCurrentSerial();
}
