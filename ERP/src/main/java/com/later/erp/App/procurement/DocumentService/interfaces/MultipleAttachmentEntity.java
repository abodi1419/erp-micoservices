package com.later.erp.App.procurement.DocumentService.interfaces;

import com.later.erp.App.suppliers.interfaces.HasMultipleAttachment;

public interface MultipleAttachmentEntity {

    public Long getId();

    public String getAttachment();

    public void setAttachment(String attachment);

    public Integer getType();

    public Integer getSerial();

    public HasMultipleAttachment getMaster();
}