package com.later.procurement.CommonModules.vat.service;


import com.later.procurement.CommonModules.vat.entity.VatPercentage;
import com.later.procurement.CommonModules.vat.mapper.VatPercentageMapper;
import com.later.procurement.CommonModules.vat.record.VatPercentageDto;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VatPercentageService {

    public List<VatPercentageDto> findAll() {
        return List.of();
    }

    public VatPercentage findById(Long id) throws ApiException {
        return null;
    }

    public VatPercentage findByIdOrElseNull(Long id) throws ApiException {
        return null;
    }


}
