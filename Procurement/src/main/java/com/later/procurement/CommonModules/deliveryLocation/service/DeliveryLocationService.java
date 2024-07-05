package com.later.procurement.CommonModules.deliveryLocation.service;


import com.later.procurement.CommonModules.deliveryLocation.entity.DeliveryLocation;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryLocationService {

    public List<DeliveryLocation> findAll() {
        return List.of();
    }

    public List<DeliveryLocation> findAllById(List<Long> ids) {
        return List.of();
    }

    public DeliveryLocation findById(Long id) throws ApiException {
        return null;
    }

    public DeliveryLocation findByIdOrElseNull(Long id) throws ApiException {
        return null;
    }
}
