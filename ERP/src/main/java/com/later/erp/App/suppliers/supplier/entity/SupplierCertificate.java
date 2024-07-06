package com.later.erp.App.suppliers.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.erp.App.suppliers.DocumentService.entity.SupplierDocument;
import com.later.erp.App.suppliers.interfaces.HasAttachment;
import com.later.erp.interfaces.HasSoftDelete;
import com.later.erp.interfaces.commonEntity.CertificateTypeInterface;
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

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_supplier_certificates")
public class SupplierCertificate implements HasSoftDelete, HasAttachment, CertificateTypeInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Supplier supplier;
    private Long certificateTypeId;
    private String certificateTypeName;
    private String certificateTypeNameAr;
    private Boolean certificateTypeRequired;
    @SQLRestriction("system_code='SP' and system_part='CERTIFICATE' and deleted=0")
    @OneToMany(mappedBy = "refId")
    private List<SupplierDocument> documents;
    private LocalDate expiryDate;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;
    @Column(columnDefinition = "bit default 0")
    private Boolean deleted = false;
    private LocalDateTime deletedAt;
    @Column(columnDefinition = "bit default 0")
    private Boolean supportType = false;
}
