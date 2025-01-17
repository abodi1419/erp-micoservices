package com.later.inventoryservice.App.inventory.inventoryTransactions.repository;

import com.later.inventoryservice.App.inventory.inventoryTransactions.entity.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTransactionRepo extends JpaRepository<InventoryTransaction, Integer> {
}
