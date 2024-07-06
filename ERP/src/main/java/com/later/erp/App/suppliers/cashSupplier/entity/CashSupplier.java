package com.later.erp.App.suppliers.cashSupplier.entity;

import com.later.erp.interfaces.commonEntity.CurrencyInterface;
import com.later.erp.interfaces.commonEntity.EmployeeInterfaces.CreatedByInterface;
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
@Table(name = "sp_cash_suppliers")
public class CashSupplier implements CurrencyInterface, CreatedByInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(unique = true)
    private Integer serial;
    @Column(unique = true)
    private String refCode;
    private Long createdById;
    private String createdByName;
    private String createdByNameAr;
    private String createdByCompanyNumber;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
