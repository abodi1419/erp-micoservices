package com.later.customerservice.CommonModules.vat.mapper;


import com.later.customerservice.CommonModules.vat.entity.VatPercentage;
import com.later.customerservice.CommonModules.vat.record.VatPercentageDto;

public class VatPercentageMapper {
    public static VatPercentageDto toRecord(VatPercentage vatPercentage) {
        return new VatPercentageDto(vatPercentage.getId(), vatPercentage.getPercentage(),
                vatPercentage.getActive(), vatPercentage.getMultiplier());
    }

    
}
