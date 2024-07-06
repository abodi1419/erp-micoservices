package com.later.erp.App.suppliers.supplier.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.erp.interfaces.commonEntity.TermTypeInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_supplier_terms")
public class SupplierTerm implements TermTypeInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JsonIgnore
    private Supplier supplier;
    private Long termTypeId;
    private String termTypeName;
    private String termTypeNameAr;
    @Column(columnDefinition = "text")
    private String term;
    @Column(columnDefinition = "text")
    private String termAr;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
