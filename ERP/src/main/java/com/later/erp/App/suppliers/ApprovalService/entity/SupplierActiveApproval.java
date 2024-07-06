package com.later.erp.App.suppliers.ApprovalService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.erp.interfaces.commonEntity.EmployeeInterfaces.ApproverInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_active_approvals")
public class SupplierActiveApproval implements ApproverInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Long approverId;
    private String approverName;
    private String approverNameAr;
    private String approverCompanyNumber;
    private Short status;
    @JsonIgnore
    private Long refId;
    private String requestRefCode;
    @JsonIgnore
    private String systemCode;
}
