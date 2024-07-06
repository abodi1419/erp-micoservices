package com.later.erp.interfaces;

import java.time.LocalDateTime;

public interface WithApproval {
    Long getId();

    Short getStatus();

    void setStatus(Short status);

    Long getCreatedById();

    LocalDateTime getLastApprovalDate();

    void setLastApprovalDate(LocalDateTime lastApprovalDate);

    String getRefCode();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
