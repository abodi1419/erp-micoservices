package com.later.erp.App.inventory.warehouses.repository;

import com.later.erp.App.inventory.warehouses.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
    @Query("select coalesce(max(i.serial), 0)+1 from Item i")
    Integer getCurrentSerial();
}
