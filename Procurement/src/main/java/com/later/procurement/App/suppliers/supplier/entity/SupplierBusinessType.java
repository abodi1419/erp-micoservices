package com.later.procurement.App.suppliers.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.procurement.interfaces.HasSoftDelete;
import com.later.procurement.interfaces.commonEntity.BusinessTypeInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class SupplierBusinessType implements HasSoftDelete, BusinessTypeInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long businessTypeId;
    private String businessTypeName;
    private String businessTypeNameAr;
    @ManyToOne
    @JsonIgnore
    private Supplier supplier;
    @ManyToOne
    private SupplierCertificate supplierCertificate;
    @Column(columnDefinition = "bit default 0")
    private Boolean deleted = false;
    private LocalDateTime deletedAt;

}
