package com.later.inventoryservice.App.items.service;

import com.later.inventoryservice.App.items.entity.MeasureUnit;
import com.later.inventoryservice.Exception.ApiException;
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

}
