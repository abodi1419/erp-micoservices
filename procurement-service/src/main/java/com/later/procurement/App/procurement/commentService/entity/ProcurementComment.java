package com.later.procurement.App.procurement.commentService.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.later.procurement.interfaces.commonEntity.EmployeeInterfaces.CreatedByInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "proc_comments")
public class ProcurementComment implements CreatedByInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createdById;
    private String createdByName;
    private String createdByNameAr;
    private String createdByCompanyNumber;
    private String action;
    @JsonIgnore
    private Long refId;
    @JsonIgnore
    private String systemCode;
    @JsonIgnore
    private String requestRefCode;
    @Column(columnDefinition = "text")
    private String comment;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
