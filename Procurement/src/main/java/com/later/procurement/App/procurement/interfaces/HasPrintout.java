package com.later.procurement.App.procurement.interfaces;


import com.later.procurement.App.procurement.DocumentService.entity.ProcurementDocument;

import java.util.List;

public interface HasPrintout {
    public Long getId();

    public List<ProcurementDocument> getPrintouts();

    public void setPrintouts(List<ProcurementDocument> printouts);
}
