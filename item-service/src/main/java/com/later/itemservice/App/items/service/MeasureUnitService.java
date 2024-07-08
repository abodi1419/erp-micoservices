package com.later.itemservice.App.items.service;

import com.later.itemservice.App.items.entity.MeasureUnit;
import com.later.itemservice.App.items.repository.MeasureUnitRepo;
import com.later.itemservice.Exception.ApiException;
import com.later.itemservice.constants.ResponseText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasureUnitService {
    private final MeasureUnitRepo measureUnitRepo;

    public List<MeasureUnit> findAll(Boolean product) {
        if (product == null)
            return measureUnitRepo.findAll();
        else {
            return measureUnitRepo.findAllByService(product);
        }
    }

    public MeasureUnit findById(Long id) throws ApiException {
        MeasureUnit measureUnit = measureUnitRepo.findById(id).orElse(null);
        if (measureUnit == null) {
            throw new ApiException(404, "Measure unit not found");
        }
        return measureUnit;
    }

    public String create(MeasureUnit measureUnit) {
        measureUnit.setId(null);
        measureUnit.setItems(null);
        measureUnitRepo.save(measureUnit);
        return "Created Successfully";
    }

    public String update(Long id, MeasureUnit measureUnit) throws ApiException {
        MeasureUnit measureUnit1 = measureUnitRepo.findById(id).orElse(null);
        if (measureUnit1 == null) {
            throw new ApiException(404, "Measure unit not found");
        }
        measureUnit1.setCode(measureUnit.getCode());
        measureUnit1.setCodeAr(measureUnit.getCodeAr());
        measureUnit1.setName(measureUnit.getName());
        measureUnit1.setNameAr(measureUnit.getNameAr());
        measureUnit1.setDescription(measureUnit.getDescription());
        measureUnit1.setDescriptionAr(measureUnit.getDescriptionAr());
        measureUnitRepo.save(measureUnit1);
        return ResponseText.UPDATED.text();
    }

//    public List<MeasureUnit> findByProduct(Boolean product) {
//        return measureUnitRepo.findAllByProduct(product);
//    }

    public List<MeasureUnit> getMeasureUnitsForItem() {
        return measureUnitRepo.findAll();
    }

    public MeasureUnit findByIdOrElseNull(Long id) {
        return measureUnitRepo.findById(id).orElse(null);
    }
}
