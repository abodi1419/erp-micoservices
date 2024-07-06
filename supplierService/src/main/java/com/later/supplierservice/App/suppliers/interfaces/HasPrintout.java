package com.later.supplierservice.App.suppliers.interfaces;

import com.later.supplierservice.App.suppliers.DocumentService.entity.SupplierDocument;
import java.util.List;

public interface HasPrintout {
    public Long getId();

    public List<SupplierDocument> getPrintouts();

    public void setPrintouts(List<SupplierDocument> printouts);
}
