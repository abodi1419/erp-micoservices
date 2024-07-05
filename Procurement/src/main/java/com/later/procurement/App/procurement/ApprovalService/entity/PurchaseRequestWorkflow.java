package com.later.procurement.App.procurement.ApprovalService.entity;



import com.later.procurement.interfaces.commonEntity.DisciplineInterface;
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
@Table(name = "proc_purchase_request_workflow")
public class PurchaseRequestWorkflow implements DivisionInterface, DisciplineInterface, ApproverInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long divisionId;
    private String divisionName;
    private String divisionNameAr;
    private Long disciplineId;
    private String disciplineName;
    private String disciplineNameAr;
    private String disciplineCode;
    private Long approverId;
    private String approverName;
    private String approverNameAr;
    private String approverCompanyNumber;
    private Short status;
    private Boolean service;
    @Column(columnDefinition = "bit default 0")
    private Boolean procurementManager = false;


}
