package com.later.supplierservice.CommonModules.vat.mapper;


import com.later.supplierservice.CommonModules.vat.entity.VatPercentage;
import com.later.supplierservice.CommonModules.vat.record.VatPercentageDto;

public class VatPercentageMapper {
    public static VatPercentageDto toRecord(VatPercentage vatPercentage) {
        return new VatPercentageDto(vatPercentage.getId(), vatPercentage.getPercentage(),
                vatPercentage.getActive(), vatPercentage.getMultiplier());
    }

    
}
