package com.later.erp.App.procurement.DocumentService.interfaces;

import com.later.erp.App.procurement.DocumentService.entity.ProcurementDocument;

import java.util.List;

public interface HasAttachment {
    public Long getId();

    public List<ProcurementDocument> getDocuments();

    public void setDocuments(List<ProcurementDocument> attachment);
}
