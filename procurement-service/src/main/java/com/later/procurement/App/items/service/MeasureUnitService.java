package com.later.procurement.App.items.service;


import com.later.procurement.App.items.entity.MeasureUnit;
import com.later.procurement.Exception.ApiException;
import com.later.procurement.constants.ResponseText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasureUnitService {

    public List<MeasureUnit> findAll(Boolean product) {
       return List.of();
    }

    public MeasureUnit findById(Long id) throws ApiException {
        return null;
    }

    public List<MeasureUnit> getMeasureUnitsForItem() {
        return List.of();
    }
}
