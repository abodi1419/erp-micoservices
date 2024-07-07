package com.later.procurement.App.procurement.PurchaseOrder.repository;


import com.later.procurement.App.procurement.PurchaseOrder.entity.PurchaseOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDetailsRepo extends JpaRepository<PurchaseOrderDetails, Long> {
}
