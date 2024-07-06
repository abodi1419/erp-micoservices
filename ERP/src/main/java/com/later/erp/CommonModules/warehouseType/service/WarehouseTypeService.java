package com.later.erp.CommonModules.warehouseType.service;

import com.later.erp.CommonModules.warehouseType.entity.WarehouseType;
import com.later.erp.CommonModules.warehouseType.repository.WarehouseTypeRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseTypeService {
    private final WarehouseTypeRepo warehouseTypeRepo;

    public List<WarehouseType> findAll() {
        return warehouseTypeRepo.findAll();
    }

    public WarehouseType findById(Long id) throws ApiException {
        WarehouseType warehouseType = warehouseTypeRepo.findById(id).orElse(null);
        if (warehouseType == null) {
            throw new ApiException(404, "Warehouse type not found");
        }
        return warehouseType;
    }

    public WarehouseType findByIdOrElseNull(Long id) throws ApiException {
        return warehouseTypeRepo.findById(id).orElse(null);
    }

    public List<WarehouseType> findAllById(List<Long> warehouseTypeIds) {
        return warehouseTypeRepo.findAllById(warehouseTypeIds);
    }
}
