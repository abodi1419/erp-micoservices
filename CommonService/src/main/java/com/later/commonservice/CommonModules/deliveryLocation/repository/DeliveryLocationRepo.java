package com.later.commonservice.CommonModules.deliveryLocation.repository;

import com.later.commonservice.CommonModules.deliveryLocation.entity.DeliveryLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryLocationRepo extends JpaRepository<DeliveryLocation, Long> {
}
