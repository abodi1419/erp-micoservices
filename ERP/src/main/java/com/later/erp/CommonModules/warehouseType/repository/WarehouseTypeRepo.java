package com.later.erp.CommonModules.warehouseType.repository;

import com.later.erp.CommonModules.warehouseType.entity.WarehouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseTypeRepo extends JpaRepository<WarehouseType, Long> {
}
