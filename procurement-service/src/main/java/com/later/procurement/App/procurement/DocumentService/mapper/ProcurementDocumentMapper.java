package com.later.procurement.App.procurement.DocumentService.mapper;

import com.later.procurement.App.procurement.DocumentService.entity.ProcurementDocument;
import com.later.procurement.App.procurement.DocumentService.model.ProcurementDocumentDto;

public class ProcurementDocumentMapper {

    public static ProcurementDocumentDto toDto(ProcurementDocument procurementDocument) {
        if (procurementDocument == null) {
            return null;
        }
        return new ProcurementDocumentDto(procurementDocument.getAttachmentType(),
                procurementDocument.getAttachmentName(), procurementDocument.getFileName(),
                procurementDocument.getFileType());
    }
}
