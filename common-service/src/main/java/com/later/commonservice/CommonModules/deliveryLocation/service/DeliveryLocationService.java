package com.later.commonservice.CommonModules.deliveryLocation.service;

import com.later.commonservice.CommonModules.deliveryLocation.entity.DeliveryLocation;
import com.later.commonservice.CommonModules.deliveryLocation.repository.DeliveryLocationRepo;
import com.later.commonservice.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryLocationService {
    private final DeliveryLocationRepo deliveryLocationRepo;

    public List<DeliveryLocation> findAll() {
        return deliveryLocationRepo.findAll();
    }

    public List<DeliveryLocation> findAllById(List<Long> ids) {
        return deliveryLocationRepo.findAllById(ids);
    }

    public DeliveryLocation findById(Long id) throws ApiException {
        DeliveryLocation deliveryLocation = deliveryLocationRepo.findById(id).orElse(null);
        if (deliveryLocation == null) {
            throw new ApiException(404, "Delivery Location not found");
        }
        return deliveryLocation;
    }

    public DeliveryLocation findByIdOrElseNull(Long id) {
        return deliveryLocationRepo.findById(id).orElse(null);
    }
}
