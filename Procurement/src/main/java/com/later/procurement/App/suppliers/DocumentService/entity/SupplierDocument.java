package com.later.procurement.App.suppliers.DocumentService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.procurement.DocumentService.entity.Document;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class SupplierDocument implements Document {
    private Long id;
    private String attachmentType;
    private String attachmentName;
    private String fileName;
    private String fileType;
    @JsonIgnore
    private String systemCode;
    @JsonIgnore
    private String systemPart;
    @JsonIgnore
    private Long refId;
    @JsonIgnore
    private Integer serial;
    @JsonIgnore
    private Boolean deleted = false;
    @JsonIgnore
    private LocalDateTime deletedAt;
}
