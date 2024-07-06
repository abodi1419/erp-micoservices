package com.later.erp.App.inventory.inventoryTransactions.service;

import com.later.erp.App.inventory.inventoryTransactions.entity.InventoryTransaction;
import com.later.erp.App.inventory.inventoryTransactions.repository.InventoryTransactionRepo;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.ResponseText;
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
