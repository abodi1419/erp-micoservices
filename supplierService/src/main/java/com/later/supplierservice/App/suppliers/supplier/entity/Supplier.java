package com.later.supplierservice.App.suppliers.supplier.entity;

import com.later.supplierservice.App.suppliers.ApprovalService.entity.SupplierActiveApproval;
import com.later.supplierservice.App.suppliers.commentService.entity.SupplierComment;
import com.later.supplierservice.interfaces.WithApproval;
import com.later.supplierservice.interfaces.commonEntity.CurrencyInterface;
import com.later.supplierservice.interfaces.commonEntity.EmployeeInterfaces.CreatedByInterface;
import com.later.supplierservice.interfaces.commonEntity.IndustryTypeInterface;
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

@Entity()
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_suppliers")
public class Supplier implements WithApproval, IndustryTypeInterface, CurrencyInterface, CreatedByInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String companyName;
    @Column(unique = true)
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
    @Column(unique = true)
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

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //    private List<SupplierApprover> approvers;
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private List<SupplierBankAccount> supplierBankAccounts;
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private List<SupplierBusinessType> supplierBusinessTypes;
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    @SQLRestriction(value = "deleted<>true")
    private List<SupplierCertificate> supplierCertificates;
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private List<SupplierContact> supplierContacts;
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private List<SupplierTerm> supplierTerms;
    // REQUIRED BY WithApproval Create live template for it
    private LocalDateTime lastApprovalDate;
    @Column(unique = true)
    private Integer serial;
    @Column(unique = true)
    private String refCode;
    private Long createdById;
    private String createdByName;
    private String createdByNameAr;
    private String createdByCompanyNumber;
    @Column(columnDefinition = "tinyint")
    private Short status;
    @Column(columnDefinition = "bit default 0")
    private Boolean active = false;
    /////////////////////////////////////////////////////////
//    @JoinFormula("request_ref_code=ref_code")
    @SQLRestriction("request_ref_code like 'SP%'")
    @OneToMany(mappedBy = "refId")
    private List<SupplierComment> comments;
    @SQLRestriction("request_ref_code like 'SP%'")
    @OneToMany(mappedBy = "refId", fetch = FetchType.EAGER)
    private List<SupplierActiveApproval> approval;


}
