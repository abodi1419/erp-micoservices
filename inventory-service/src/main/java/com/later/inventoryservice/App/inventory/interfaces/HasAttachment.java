package com.later.inventoryservice.App.inventory.interfaces;

import com.later.inventoryservice.App.inventory.DocumentService.entity.InventoryDocument;

import java.util.List;

public interface HasAttachment {
    public Long getId();

    public List<InventoryDocument> getDocuments();

    public void setDocuments(List<InventoryDocument> attachment);
}
