package com.later.procurement.App.suppliers.supplier.entity;


import com.later.procurement.interfaces.WithApproval;
import com.later.procurement.interfaces.commonEntity.CurrencyInterface;
import com.later.procurement.interfaces.commonEntity.EmployeeInterfaces.CreatedByInterface;
import com.later.procurement.interfaces.commonEntity.IndustryTypeInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class Supplier implements WithApproval, IndustryTypeInterface, CurrencyInterface, CreatedByInterface {
    private Long id;
    private String companyName;
    private String companyNameAr;
    private String tradingName;
    private String tradingNameAr;
    private Long industryTypeId;
    private String industryTypeName;
    private String industryTypeNameAr;
    private String size;
    private LocalDate incorporationDate;
    private Long currencyId;
    private String currencyName;
    private String currencyNameAr;
    private String currencyShortName;
    private String currencyShortNameAr;
    private String address;
    private String telephoneNumber;
    private String email;
    private String fax;
    private String phone;
    private String vatNumber;
    private String website;
    private String commercialRegister;
    private Boolean local;
    private Long cityId;
    private String cityName;
    private String cityNameAr;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<SupplierBankAccount> supplierBankAccounts;
    private List<SupplierBusinessType> supplierBusinessTypes;
    private List<SupplierCertificate> supplierCertificates;
    private List<SupplierContact> supplierContacts;
    private List<SupplierTerm> supplierTerms;
    // REQUIRED BY WithApproval Create live template for it
    private LocalDateTime lastApprovalDate;
    private Integer serial;
    private String refCode;
    private Long createdById;
    private String createdByName;
    private String createdByNameAr;
    private String createdByCompanyNumber;
    private Short status;
    private Boolean active = false;
    /////////////////////////////////////////////////////////
//    @JoinFormula("request_ref_code=ref_code")

}
