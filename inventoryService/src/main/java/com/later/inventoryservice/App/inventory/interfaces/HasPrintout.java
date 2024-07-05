package com.later.inventoryservice.App.inventory.interfaces;


import com.later.inventoryservice.App.inventory.DocumentService.entity.InventoryDocument;

import java.util.List;

public interface HasPrintout {
    public Long getId();

    public List<InventoryDocument> getPrintouts();

    public void setPrintouts(List<InventoryDocument> printouts);
}
