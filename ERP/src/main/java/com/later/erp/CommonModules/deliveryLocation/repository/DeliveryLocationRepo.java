package com.later.erp.CommonModules.deliveryLocation.repository;

import com.later.erp.CommonModules.deliveryLocation.entity.DeliveryLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryLocationRepo extends JpaRepository<DeliveryLocation, Long> {
}
