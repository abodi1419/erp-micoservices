package com.later.erp.interfaces;

import java.time.LocalDateTime;

public interface HasSoftDelete {
    public Long getId();

    public Boolean getDeleted();

    public LocalDateTime getDeletedAt();
}