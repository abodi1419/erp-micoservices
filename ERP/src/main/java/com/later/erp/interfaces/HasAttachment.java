package com.later.erp.interfaces;

import java.util.List;

public interface HasAttachment<T> {
    public Long getId();

    public List<T> getDocuments();

    public void setDocuments(List<T> attachment);
}
