package com.later.erp.App.suppliers.interfaces;

import com.later.erp.CommonServices.DocumentService.entity.Document;

import java.util.List;

public interface HasPrintout {
    public Long getId();

    public List<Document> getPrintouts();

    public void setPrintouts(List<Document> printouts);
}
