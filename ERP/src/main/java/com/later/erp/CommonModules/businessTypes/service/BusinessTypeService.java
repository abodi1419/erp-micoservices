package com.later.erp.CommonModules.businessTypes.service;

import com.later.erp.CommonModules.businessTypes.entity.BusinessType;
import com.later.erp.CommonModules.businessTypes.repository.BusinessTypeRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessTypeService {
    private final BusinessTypeRepo businessTypeRepo;

    public List<BusinessType> findAll() {
        return businessTypeRepo.findAll();
    }

    public BusinessType findById(Long id) throws ApiException {
        BusinessType businessType = businessTypeRepo.findById(id).orElse(null);
        if (businessType == null) {
            throw new ApiException(404, "Business type not found");
        }
        return businessType;
    }

    public BusinessType findByIdOrElseNull(Long id) throws ApiException {
        return businessTypeRepo.findById(id).orElse(null);
    }
}
