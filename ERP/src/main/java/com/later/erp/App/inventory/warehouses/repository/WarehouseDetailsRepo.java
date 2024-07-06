package com.later.erp.App.inventory.warehouses.repository;

import com.later.erp.App.inventory.warehouses.entity.WarehouseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseDetailsRepo extends JpaRepository<WarehouseDetails, Long> {
    List<WarehouseDetails> findByWarehouseId(Long warehouseId);
}
