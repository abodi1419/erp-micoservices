package com.later.erp.App.suppliers.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.erp.interfaces.commonEntity.BankInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_supplier_bank_accounts")
public class SupplierBankAccount implements BankInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JsonIgnore
    private Supplier supplier;
    private Long bankId;
    private String bankName;
    private String bankNameAr;
    @Column(columnDefinition = "bit default 1")
    private Boolean localBank;
    @Column(unique = true)
    private String accountIBAN;
    private String accountNo;
    private String accountName;
    private String accountNameAr;
    @Column(columnDefinition = "bit default 1")
    private Boolean local;
    @Column(columnDefinition = "tinyint")
    private Short preferred;
}
