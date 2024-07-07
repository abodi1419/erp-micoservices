package com.later.supplierservice.constants;

public enum DocumentType {

    ATTACHMENT("Attachment", 1),
    COST_CONTROL_ATTACHMENT("Cost Control Attachment", 2),
    FINANCE_ATTACHMENT("Finance Attachment", 3),
    ADDITIONAL_ATTACHMENT("Additional Attachment", 4),
    ;
    private final String type;
    private final Integer code;

    private DocumentType(String type, Integer code) {
        this.type = type;
        this.code = code;
    }

    public String type() {
        return type;
    }

    public Integer code() {
        return code;
    }
}
