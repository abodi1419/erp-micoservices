package com.later.procurement.CommonModules.industryTypes.service;


import com.later.procurement.CommonModules.industryTypes.entity.IndustryType;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndustryTypeService {
    public List<IndustryType> findAll() {
        return List.of();
    }

    public IndustryType findById(Long id) throws ApiException {
        return null;
    }

    public IndustryType findByIdOrElseNull(Long id) throws ApiException {
        return null;
    }
}
