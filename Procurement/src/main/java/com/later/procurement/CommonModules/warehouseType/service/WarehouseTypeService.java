package com.later.procurement.CommonModules.warehouseType.service;


import com.later.procurement.CommonModules.warehouseType.entity.WarehouseType;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseTypeService {

    public List<WarehouseType> findAll() {
        return List.of();
    }

    public WarehouseType findById(Long id) throws ApiException {
        return null;
    }

    public WarehouseType findByIdOrElseNull(Long id) throws ApiException {
        return null;
    }

    public List<WarehouseType> findAllById(List<Long> warehouseTypeIds) {
       return List.of();
    }
}
