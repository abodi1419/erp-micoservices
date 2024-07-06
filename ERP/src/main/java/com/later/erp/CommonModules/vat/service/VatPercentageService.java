package com.later.erp.CommonModules.vat.service;

import com.later.erp.CommonModules.vat.entity.VatPercentage;
import com.later.erp.CommonModules.vat.mapper.VatPercentageMapper;
import com.later.erp.CommonModules.vat.record.VatPercentageDto;
import com.later.erp.CommonModules.vat.repository.VatPercentageRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VatPercentageService {
    private final VatPercentageRepo vatPercentageRepo;

    public List<VatPercentageDto> findAll() {
        return vatPercentageRepo.findAllActive().stream().map(VatPercentageMapper::toRecord).toList();
    }

    public VatPercentage findById(Long id) throws ApiException {
        VatPercentage vatPercentage = vatPercentageRepo.findById(id).orElse(null);
        if (vatPercentage == null) {
            throw new ApiException(404, "Vat percentage not found");
        }
        return vatPercentage;
    }

    public VatPercentage findByIdOrElseNull(Long id) throws ApiException {
        return vatPercentageRepo.findById(id).orElse(null);
    }


}
