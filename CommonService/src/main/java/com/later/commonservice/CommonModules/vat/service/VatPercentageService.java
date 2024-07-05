package com.later.commonservice.CommonModules.vat.service;

import com.later.commonservice.CommonModules.vat.entity.VatPercentage;
import com.later.commonservice.CommonModules.vat.mapper.VatPercentageMapper;
import com.later.commonservice.CommonModules.vat.record.VatPercentageDto;
import com.later.commonservice.CommonModules.vat.repository.VatPercentageRepo;
import com.later.commonservice.Exception.ApiException;
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

    public VatPercentage findByIdOrElseNull(Long id) {
        return vatPercentageRepo.findById(id).orElse(null);
    }


}
