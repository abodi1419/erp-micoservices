package com.later.procurement.App.suppliers.cashSupplier.entity;


import com.later.procurement.interfaces.commonEntity.CurrencyInterface;
import com.later.procurement.interfaces.commonEntity.EmployeeInterfaces.CreatedByInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class CashSupplier implements CurrencyInterface, CreatedByInterface {
    private Long id;
    private String companyName;
    private String companyNameAr;
    private Long currencyId;
    private String currencyName;
    private String currencyNameAr;
    private String currencyShortName;
    private String currencyShortNameAr;
    private String vatNumber;
    private String commercialRegister;
    private String address;
    private String email;
    private String fax;
    private String phone;
    private Integer serial;
    private String refCode;
    private Long createdById;
    private String createdByName;
    private String createdByNameAr;
    private String createdByCompanyNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
