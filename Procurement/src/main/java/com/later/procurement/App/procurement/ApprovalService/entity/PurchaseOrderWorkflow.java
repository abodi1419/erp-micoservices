package com.later.procurement.App.procurement.ApprovalService.entity;


import com.later.procurement.interfaces.commonEntity.CostCenterInterface;
import com.later.procurement.interfaces.commonEntity.DivisionInterface;
import com.later.procurement.interfaces.commonEntity.EmployeeInterfaces.ApproverInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "proc_purchase_order_workflows")
public class PurchaseOrderWorkflow implements ApproverInterface, DivisionInterface, CostCenterInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long costCenterId;
    private String costCenterName;
    private String costCenterNameAr;
    private String costCenterRefCode;
    private Long divisionId;
    private String divisionName;
    private String divisionNameAr;
    private Long approverId;
    private String approverName;
    private String approverNameAr;
    private String approverCompanyNumber;
    private Short status;
    private Double minValue;
    private Double maxValue;
    private Boolean pettyCash;
    private Boolean procurementManager;
}
