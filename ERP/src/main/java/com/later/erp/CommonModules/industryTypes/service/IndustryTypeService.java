package com.later.erp.CommonModules.industryTypes.service;

import com.later.erp.CommonModules.industryTypes.entity.IndustryType;
import com.later.erp.CommonModules.industryTypes.repository.IndustryTypeRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndustryTypeService {
    private final IndustryTypeRepo industryTypeRepo;

    public List<IndustryType> findAll() {
        return industryTypeRepo.findAll();
    }

    public IndustryType findById(Long id) throws ApiException {
        IndustryType industryType = industryTypeRepo.findById(id).orElse(null);
        if (industryType == null) {
            throw new ApiException(404, "Industry type not found");
        }
        return industryType;
    }

    public IndustryType findByIdOrElseNull(Long id) throws ApiException {
        return industryTypeRepo.findById(id).orElse(null);
    }
}
