package com.later.procurement.App.procurement.DocumentService.model;

import com.later.procurement.App.procurement.DocumentService.entity.ProcurementDocument;

import java.io.Serializable;

/**
 * DTO for {@link ProcurementDocument}
 */
public record ProcurementDocumentDto(String attachmentType, String attachmentName, String fileName,
                                     String fileType) implements Serializable {
}