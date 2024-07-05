package com.later.procurement.App.procurement.PurchaseOrder.entity;



import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementActiveApproval;
import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementApprovalSpecialCondition;
import com.later.procurement.App.procurement.DocumentService.entity.ProcurementDocument;
import com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequest;
import com.later.procurement.App.procurement.commentService.entity.ProcurementComment;
import com.later.procurement.App.procurement.interfaces.HasAttachment;
import com.later.procurement.interfaces.WithApproval;
import com.later.procurement.interfaces.commonEntity.*;
import com.later.procurement.interfaces.commonEntity.EmployeeInterfaces.CreatedByInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "proc_purchase_orders")
public class PurchaseOrder implements WithApproval, HasAttachment, CostCenterInterface, DepartmentInterface, DivisionInterface, CreatedByInterface, CurrencyInterface, DeliveryLocationInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long costCenterId;
    private String costCenterName;
    private String costCenterNameAr;
    private String costCenterRefCode;
    private Long departmentId;
    private String departmentName;
    private String departmentNameAr;
    private String departmentRefCode;
    private Long divisionId;
    private String divisionName;
    private String divisionNameAr;
    @ManyToOne()
    @JoinColumn(name = "purchase_request_ref_code", referencedColumnName = "refCode")
    private PurchaseRequest purchaseRequest;
    // Purchase, Service
    private Short type;
    @Column(columnDefinition = "bit default 0")
    private Boolean revision;
    private Integer revisionSerial;
    @ManyToOne
    private PurchaseOrder revisedPurchaseOrder;
    private Boolean pettyCash;
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
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Long supplierId;
    private String supplierName;
    private String supplierNameAr;
    private String supplierVatNumber;
    private String supplierCommercialRegister;
    private String supplierRefCode;
    private Boolean localSupplier;
    private Long supplierContactId;
    private String supplierContactName;
    private String supplierContactNameAr;
    private String supplierContactJobTitle;
    private String supplierContactJobTitleAr;
    private String supplierContactEmail;
    private String supplierContactPhone;
    private Long currencyId;
    private String currencyName;
    private String currencyNameAr;
    private String currencyShortName;
    private String currencyShortNameAr;
    private Long deliveryLocationId;
    private String deliveryLocationName;
    private String deliveryLocationNameAr;
    private String deliveryLocationAddress;
    private String deliveryLocationAddressAr;
    private String deliveryLocationGoogleMapAddress;
    @SQLRestriction("system_code='PO' and deleted='false'")
    @OneToMany(mappedBy = "refId")
    private List<ProcurementDocument> documents;
    @SQLRestriction("system_code='PO'")
    @OneToMany(mappedBy = "refId")
    private List<ProcurementComment> comments;
    @SQLRestriction("system_code='PO'")
    @OneToMany(mappedBy = "refId")
    private List<ProcurementActiveApproval> approval;
    @Transient
    private List<ProcurementApprovalSpecialCondition> specialConditions;
    private BigDecimal advancePayment = BigDecimal.ZERO;
    private BigDecimal shippingCost = BigDecimal.ZERO;
    private BigDecimal shippingVatPercentage;
    private BigDecimal shippingVatMultiplier;
    private BigDecimal shippingVatAmount;
    private BigDecimal shippingTotal;
    private BigDecimal itemsTotalVatAmount;
    private BigDecimal itemsTotal;
    private BigDecimal grandTotal;
    @Column(columnDefinition = "text")
    private String deliveryTerms;
    @Column(columnDefinition = "text")
    private String paymentTerms;
    @Column(columnDefinition = "text")
    private String warrantyTerms;
    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER)
    private List<PurchaseOrderDetails> purchaseOrderDetails;


}
