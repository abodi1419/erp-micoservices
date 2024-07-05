package com.later.procurement.CommonModules.businessTypes.service;


import com.later.procurement.CommonModules.businessTypes.entity.BusinessType;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessTypeService {

    public List<BusinessType> findAll() {
        return List.of();
    }

    public BusinessType findById(Long id) throws ApiException {
        return null;
    }

    public BusinessType findByIdOrElseNull(Long id) throws ApiException {
        return null;
    }
}
