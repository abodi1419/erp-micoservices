package com.later.commonservice.CommonModules.company.costCenter.service;

import com.later.commonservice.CommonModules.company.costCenter.entity.CostCenter;
import com.later.commonservice.CommonModules.company.costCenter.repository.CostCenterRepo;
import com.later.commonservice.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostCenterService {
    private final CostCenterRepo costCenterRepo;

    public List<CostCenter> findAll() {
        return costCenterRepo.findAll();
    }

    public CostCenter findById(Long id) throws ApiException {
        CostCenter costCenter = costCenterRepo.findById(id).orElse(null);
        if (costCenter == null) {
            throw new ApiException(404, "Cost center not found");
        }
        return costCenter;
    }

    public CostCenter findByIdOrElseNull(Long id) {
        return costCenterRepo.findById(id).orElse(null);
    }
}
