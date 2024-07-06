package com.later.erp.App.procurement.ApprovalService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "proc_approval_special_conditions")
public class ProcurementApprovalSpecialCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String typeAr;
    private Integer typeId;
    @Column(columnDefinition = "text")
    private String typeDesc;
    @Column(columnDefinition = "text")
    private String typeDescAr;
    private Short status;
    private String systemCode;
    @Column(columnDefinition = "bit default true")
    private Boolean active;

}
