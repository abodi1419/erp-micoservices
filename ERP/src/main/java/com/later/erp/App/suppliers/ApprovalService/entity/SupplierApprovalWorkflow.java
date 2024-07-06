package com.later.erp.App.suppliers.ApprovalService.entity;


import com.later.erp.interfaces.commonEntity.EmployeeInterfaces.ApproverInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_approval_workflows")
public class SupplierApprovalWorkflow implements ApproverInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long approverId;
    private String approverName;
    private String approverNameAr;
    private String approverCompanyNumber;
    private Short status;


}
