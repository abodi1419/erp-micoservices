package com.later.supplierservice.DocumentService.entity;
import com.later.supplierservice.interfaces.HasSoftDelete;

import java.time.LocalDateTime;


public interface Document extends HasSoftDelete {

    public Long getId();

    public String getAttachmentType();

    public String getAttachmentName();

    public String getFileName();

    public String getFileType();

    public String getSystemCode();

    public String getSystemPart();

    public Long getRefId();

    public Integer getSerial();

    public Boolean getDeleted();

    public LocalDateTime getDeletedAt();

    public void setId(Long id);

    public void setAttachmentType(String attachmentType);

    public void setAttachmentName(String attachmentName);

    public void setFileName(String fileName);

    public void setFileType(String fileType);

    public void setSystemCode(String systemCode);

    public void setSystemPart(String systemPart);

    public void setRefId(Long refId);

    public void setSerial(Integer serial);

    public void setDeleted(Boolean deleted);

    public void setDeletedAt(LocalDateTime deletedAt);

}
