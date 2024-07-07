package com.later.procurement.App.suppliers.interfaces;


import com.later.procurement.DocumentService.entity.Document;

import java.util.List;

public interface HasPrintout {
    public Long getId();

    public List<Document> getPrintouts();

    public void setPrintouts(List<Document> printouts);
}
