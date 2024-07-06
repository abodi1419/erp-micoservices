package com.later.erp.App.suppliers.DocumentService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.erp.CommonServices.DocumentService.entity.Document;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_documents")
public class SupplierDocument implements Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attachmentType;
    private String attachmentName;
    @Column(unique = true)
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
    @Column(columnDefinition = "bit default 0")
    @JsonIgnore
    private Boolean deleted = false;
    @JsonIgnore
    private LocalDateTime deletedAt;
}
