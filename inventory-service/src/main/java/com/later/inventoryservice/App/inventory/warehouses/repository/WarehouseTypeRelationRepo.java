package com.later.inventoryservice.App.inventory.warehouses.repository;

import com.later.inventoryservice.App.inventory.warehouses.entity.WarehouseTypeRelation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseTypeRelationRepo extends JpaRepository<WarehouseTypeRelation, Long> {

    @Modifying
    @Transactional
    void deleteAllByWarehouse_Id(Long id);
}
