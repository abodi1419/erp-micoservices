package com.later.procurement.App.procurement.PurchaseRequest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementActiveApproval;
import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementApprovalSpecialCondition;
import com.later.procurement.App.procurement.DocumentService.entity.ProcurementDocument;
import com.later.procurement.App.procurement.PurchaseOrder.entity.PurchaseOrder;
import com.later.procurement.App.procurement.commentService.entity.ProcurementComment;
import com.later.procurement.App.procurement.interfaces.HasAttachment;
import com.later.procurement.interfaces.WithApproval;
import com.later.procurement.interfaces.commonEntity.CostCenterInterface;
import com.later.procurement.interfaces.commonEntity.DepartmentInterface;
import com.later.procurement.interfaces.commonEntity.DisciplineInterface;
import com.later.procurement.interfaces.commonEntity.DivisionInterface;
import com.later.procurement.interfaces.commonEntity.EmployeeInterfaces.BuyerInterface;
import com.later.procurement.interfaces.commonEntity.EmployeeInterfaces.CreatedByInterface;
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

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "proc_purchase_requests")
public class PurchaseRequest implements HasAttachment, WithApproval, CostCenterInterface, DivisionInterface, DepartmentInterface, DisciplineInterface, CreatedByInterface, BuyerInterface {
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
    private Long disciplineId;
    private String disciplineName;
    private String disciplineNameAr;
    private String disciplineCode;
    private Boolean permanentItems;
    private LocalDate deliveryDate;
    @SQLRestriction("system_code='PR' and system_part='master' and deleted='false'")
    @OneToMany(mappedBy = "refId")
    private List<ProcurementDocument> documents;
    @SQLRestriction("system_code='PR'")
    @OneToMany(mappedBy = "refId")
    private List<ProcurementComment> comments;
    @SQLRestriction("system_code='PR'")
    @OneToMany(mappedBy = "refId")
    private List<ProcurementActiveApproval> approval;
    @Column(columnDefinition = "text")
    private String generalRemarks;
    @Column(columnDefinition = "text")
    private String deliveryTerms;
    private Boolean service;
    private Double total;
    // APPROVAL
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
    @OneToMany(mappedBy = "purchaseRequest")
    private List<PurchaseRequestDetails> purchaseRequestDetails;
    private Long buyerId;
    private String buyerName;
    private String buyerNameAr;
    private String buyerCompanyNumber;
    @Transient
    private List<ProcurementApprovalSpecialCondition> specialConditions;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "purchaseRequest")
    @JsonIgnore
    private List<PurchaseOrder> purchaseOrders;


}
