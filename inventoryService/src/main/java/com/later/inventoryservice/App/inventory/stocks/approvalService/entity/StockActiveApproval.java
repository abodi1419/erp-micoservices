package com.later.inventoryservice.App.inventory.stocks.approvalService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.inventoryservice.interfaces.commonEntity.EmployeeInterfaces.ApproverInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "war_active_approvals")
public class StockActiveApproval implements ApproverInterface {
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
