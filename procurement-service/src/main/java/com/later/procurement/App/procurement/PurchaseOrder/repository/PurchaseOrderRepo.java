package com.later.procurement.App.procurement.PurchaseOrder.repository;


import com.later.procurement.App.procurement.PurchaseOrder.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {
}
