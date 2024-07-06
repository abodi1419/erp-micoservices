package com.later.erp.App.procurement.PurchaseRequest.repository;

import com.later.erp.App.procurement.PurchaseRequest.entity.PurchaseRequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestDetailsRepo extends JpaRepository<PurchaseRequestDetails, Long> {
}
