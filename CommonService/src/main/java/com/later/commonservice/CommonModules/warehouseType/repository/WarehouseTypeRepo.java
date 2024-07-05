package com.later.commonservice.CommonModules.warehouseType.repository;

import com.later.commonservice.CommonModules.warehouseType.entity.WarehouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseTypeRepo extends JpaRepository<WarehouseType, Long> {
}
