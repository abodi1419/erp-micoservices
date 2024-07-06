package com.later.erp.App.procurement.DocumentService.interfaces;

import com.later.erp.Authorization.Security.Auth.entities.Employee;

import java.time.LocalDateTime;

public interface WithApproval {
    Long getId();

    Short getStatus();

    void setStatus(Short status);

    Employee getCreatedBy();

    LocalDateTime getLastApprovalDate();

    void setLastApprovalDate(LocalDateTime lastApprovalDate);

    String getRefCode();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
