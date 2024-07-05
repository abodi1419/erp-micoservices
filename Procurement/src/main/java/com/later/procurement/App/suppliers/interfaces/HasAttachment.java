package com.later.procurement.App.suppliers.interfaces;


import com.later.procurement.App.suppliers.DocumentService.entity.SupplierDocument;

import java.util.List;

public interface HasAttachment {
    public Long getId();

    public List<SupplierDocument> getDocuments();

    public void setDocuments(List<SupplierDocument> attachment);
}
