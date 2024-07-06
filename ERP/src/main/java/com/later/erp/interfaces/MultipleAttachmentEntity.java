package com.later.erp.interfaces;

public interface MultipleAttachmentEntity {

    public Long getId();

    public String getAttachment();

    public void setAttachment(String attachment);

    public Integer getType();

    public Integer getSerial();

    public HasMultipleAttachment getMaster();
}
