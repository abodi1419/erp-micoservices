package com.later.procurement.App.suppliers.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.procurement.interfaces.commonEntity.BankInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class SupplierBankAccount implements BankInterface {
    private Long id;
    private Supplier supplier;
    private Long bankId;
    private String bankName;
    private String bankNameAr;
    private Boolean localBank;
    private String accountIBAN;
    private String accountNo;
    private String accountName;
    private String accountNameAr;
    private Boolean local;
    private Short preferred;
}
