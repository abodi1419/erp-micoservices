package com.later.procurement.App.suppliers.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.later.procurement.App.suppliers.DocumentService.entity.SupplierDocument;
import com.later.procurement.App.suppliers.interfaces.HasAttachment;
import com.later.procurement.interfaces.HasSoftDelete;
import com.later.procurement.interfaces.commonEntity.CertificateTypeInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class SupplierCertificate implements HasSoftDelete, HasAttachment, CertificateTypeInterface {
    private Long id;
    private Supplier supplier;
    private Long certificateTypeId;
    private String certificateTypeName;
    private String certificateTypeNameAr;
    private Boolean certificateTypeRequired;
    private List<SupplierDocument> documents;
    private LocalDate expiryDate;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Boolean deleted = false;
    private LocalDateTime deletedAt;
    private Boolean supportType = false;
}
