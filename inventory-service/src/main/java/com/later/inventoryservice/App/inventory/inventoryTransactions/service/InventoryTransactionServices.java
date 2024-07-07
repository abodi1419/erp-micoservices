package com.later.inventoryservice.App.inventory.inventoryTransactions.service;

import com.later.inventoryservice.App.inventory.inventoryTransactions.entity.InventoryTransaction;
import com.later.inventoryservice.App.inventory.inventoryTransactions.repository.InventoryTransactionRepo;
import com.later.inventoryservice.Exception.ApiException;
import com.later.inventoryservice.constants.ResponseText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryTransactionServices {

    private final InventoryTransactionRepo inventoryTransactionRepo;

    public String create(InventoryTransaction inventoryTransaction) throws ApiException {
        inventoryTransactionRepo.save(inventoryTransaction);
        return ResponseText.CREATED.text();

    }


}
