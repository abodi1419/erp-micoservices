package com.later.erp.App.procurement.DocumentService.interfaces;

import com.later.erp.App.procurement.DocumentService.entity.ProcurementDocument;

import java.util.List;

public interface HasPrintout {
    public Long getId();

    public List<ProcurementDocument> getPrintouts();

    public void setPrintouts(List<ProcurementDocument> printouts);
}
