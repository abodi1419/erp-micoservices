package com.later.procurement.App.suppliers.supplier.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.procurement.interfaces.commonEntity.TermTypeInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class SupplierTerm implements TermTypeInterface {
    private Long id;
    @JsonIgnore
    private Supplier supplier;
    private Long termTypeId;
    private String termTypeName;
    private String termTypeNameAr;
    private String term;
    private String termAr;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}
